import re
import os

BD_TEST_DIR = r"C:\Users\Ejecutivo2.ANTUAN\Documents\backend_transitorio\bd_test"
OUT_DIR = r"C:\Users\Ejecutivo2.ANTUAN\Documents\backend_transitorio\transitorio\src\main\java\com\antuan_midleware\core\model"
PACKAGE = "com.antuan_midleware.core.model"

# Skip entities that already exist or shouldn't be overridden
SKIP_TABLES = {"empresas", "parametros", "enc_solicosto", "det_solicosto", "acc_solicosto", "tal_solicosto", "log_solicosto"}

os.makedirs(OUT_DIR, exist_ok=True)

def to_pascal_case(s):
    return ''.join(word.capitalize() for word in s.lower().replace('-', '_').split('_'))

def to_camel_case(s):
    pascal = to_pascal_case(s)
    if not pascal: return ""
    return pascal[0].lower() + pascal[1:]

def map_type(col_name, sql_type):
    sql_type = sql_type.upper()
    col_upper = col_name.upper()
    
    if "VARCHAR" in sql_type or "CHAR" in sql_type:
        return "String"
    if "DATETIME" in sql_type or "DATE" in sql_type or "TIMESTAMP" in sql_type:
        return "LocalDateTime"
    if "BIT" in sql_type or "TINYINT(1)" in sql_type:
        return "Boolean"
    if "LONGTEXT" in sql_type or "TEXT" in sql_type:
        return "String" 
    
    if "INT" in sql_type:
        if "ID" in col_upper or "RUT" in col_upper or "CODIGO" in col_upper or "NRO" in col_upper or "NUMERO" in col_upper or "CLIENTE" in col_upper or "PROVEEDOR" in col_upper:
            return "Long"
        return "Integer"
        
    if "FLOAT" in sql_type or "DOUBLE" in sql_type or "DECIMAL" in sql_type or "NUMERIC" in sql_type:
        money_words = ["PRECIO", "COSTO", "TOT", "MONTO", "VALOR", "DEBE", "HABER", "SALDO", "SUB", "DESCUENTO", "RECARGO", "IVA"]
        is_money = any(w in col_upper for w in money_words)
        is_id = "RUT" in col_upper or "NRO" in col_upper or "NUM" in col_upper or "CLIENTE" in col_upper or "PROVEEDOR" in col_upper or "ID" in col_upper
        
        if is_id:
            return "Long"
        elif is_money:
            return "BigDecimal"
        else:
            return "Double"
            
    return "String"

def parse_sql(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    tables = []
    
    pattern = r"CREATE TABLE\s+(?:\w+\.)?`?(\w+)`?\s*\((.*?)\)\s*(?:ENGINE\s*=\s*\w+)?(?:\s*DEFAULT\s*CHARSET\s*=\s*\w+)?;"
    matches = re.finditer(pattern, content, re.IGNORECASE | re.DOTALL)
    
    for match in matches:
        table_name = match.group(1)
        body = match.group(2)
        
        columns = []
        pk_cols = []
        
        lines = body.split(',')
        reconstructed_lines = []
        curr_line = ""
        paren_count = 0
        for chunk in lines:
            if curr_line:
                curr_line += "," + chunk
            else:
                curr_line = chunk
            paren_count += chunk.count('(') - chunk.count(')')
            if paren_count == 0:
                reconstructed_lines.append(curr_line.strip())
                curr_line = ""
                
        for line in reconstructed_lines:
            line = line.strip()
            if not line or line.startswith("--"):
                continue
                
            if line.upper().startswith("PRIMARY KEY"):
                pk_match = re.search(r"\((.*?)\)", line)
                if pk_match:
                    pks = [p.strip().strip("`").strip("'").strip('"') for p in pk_match.group(1).split(',')]
                    pk_cols.extend(pks)
                continue
                
            if line.upper().startswith("KEY") or line.upper().startswith("CONSTRAINT") or line.upper().startswith("UNIQUE"):
                continue
                
            parts = line.split()
            if len(parts) >= 2:
                col_name = parts[0].strip("`").strip("'").strip('"')
                col_type = parts[1]
                
                length_match = re.search(r"\((\d+)(?:,\d+)?\)", col_type)
                length = length_match.group(1) if length_match else None
                
                is_lob = "LONGTEXT" in col_type.upper() or "TEXT" in col_type.upper()
                
                columns.append({
                    "original_name": col_name,
                    "camel_case": to_camel_case(col_name),
                    "java_type": map_type(col_name, col_type),
                    "length": length,
                    "is_lob": is_lob
                })
                
        tables.append({
            "table_name": table_name,
            "class_name": to_pascal_case(table_name),
            "columns": columns,
            "pk_cols": pk_cols
        })
        
    return tables

def generate_java(table):
    class_name = table["class_name"]
    imports = {
        "jakarta.persistence.Column",
        "jakarta.persistence.Entity",
        "jakarta.persistence.Table",
        "java.io.Serializable",
        "lombok.AllArgsConstructor",
        "lombok.Getter",
        "lombok.NoArgsConstructor",
        "lombok.Setter"
    }
    
    has_big_decimal = any(c["java_type"] == "BigDecimal" for c in table["columns"])
    if has_big_decimal:
        imports.add("java.math.BigDecimal")
        
    has_local_date_time = any(c["java_type"] == "LocalDateTime" for c in table["columns"])
    if has_local_date_time:
        imports.add("java.time.LocalDateTime")
        
    has_lob = any(c["is_lob"] for c in table["columns"])
    if has_lob:
        imports.add("jakarta.persistence.Lob")
        
    pk_cols = table["pk_cols"]
    is_composite_pk = len(pk_cols) > 1
    has_pk = len(pk_cols) > 0
    
    code = f"package {PACKAGE};\n\n"
    
    embeddable_class_code = ""
    embeddable_class_name = f"{class_name}Id"
    
    if is_composite_pk:
        imports.add("jakarta.persistence.EmbeddedId")
        imports.add("jakarta.persistence.Embeddable")
        
        id_fields = ""
        for p_col in pk_cols:
            col_info = next((c for c in table["columns"] if c["original_name"].upper() == p_col.upper()), None)
            if col_info:
                id_fields += f"    @Column(name = \"{col_info['original_name']}\")\n"
                id_fields += f"    private {col_info['java_type']} {col_info['camel_case']};\n\n"

        embed_imports = {
            "jakarta.persistence.Column",
            "jakarta.persistence.Embeddable",
            "java.io.Serializable",
            "lombok.AllArgsConstructor",
            "lombok.Getter",
            "lombok.NoArgsConstructor",
            "lombok.Setter",
            "lombok.EqualsAndHashCode"
        }
        if has_local_date_time and any(c["java_type"] == "LocalDateTime" for c in table["columns"] if c["original_name"].upper() in [p.upper() for p in pk_cols]):
            embed_imports.add("java.time.LocalDateTime")
        if has_big_decimal and any(c["java_type"] == "BigDecimal" for c in table["columns"] if c["original_name"].upper() in [p.upper() for p in pk_cols]):
            embed_imports.add("java.math.BigDecimal")
            
        embeddable_class_code = f"package {PACKAGE};\n\n"
        for imp in sorted(list(embed_imports)):
            embeddable_class_code += f"import {imp};\n"
            
        embeddable_class_code += f"""
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class {embeddable_class_name} implements Serializable {{
{id_fields}
}}
"""
    elif has_pk:
        imports.add("jakarta.persistence.Id")

    for imp in sorted(list(imports)):
        code += f"import {imp};\n"
        
    code += "\n@Entity\n"
    code += f"@Table(name = \"{table['table_name']}\")\n"
    code += "@Getter\n@Setter\n@NoArgsConstructor\n@AllArgsConstructor\n"
    code += f"public class {class_name} implements Serializable {{\n\n"
    
    fields_code = ""

    if is_composite_pk:
        fields_code += f"    @EmbeddedId\n    private {embeddable_class_name} id;\n\n"

    for col in table["columns"]:
        if is_composite_pk and col["original_name"].upper() in [p.upper() for p in pk_cols]:
            continue 
            
        annos = []
        if not is_composite_pk and has_pk and col["original_name"].upper() == pk_cols[0].upper():
            annos.append("@Id")
            
        if col["is_lob"]:
            annos.append("@Lob")
            
        col_anno = f"@Column(name = \"{col['original_name']}\""
        if col["length"] and col["java_type"] == "String" and not col["is_lob"]:
            col_anno += f", length = {col['length']}"
        col_anno += ")"
        annos.append(col_anno)
        
        for a in annos:
            fields_code += f"    {a}\n"
        
        fields_code += f"    private {col['java_type']} {col['camel_case']};\n\n"
        
    code += fields_code
    code += "}\n"
    
    return code, embeddable_class_code, embeddable_class_name, is_composite_pk

def main():
    print("Iniciando parseo y generacion con Lombok...")
    tables = []
    
    for filename in os.listdir(BD_TEST_DIR):
        if filename.endswith(".sql"):
            print(f" -> Leyendo {filename}")
            tables.extend(parse_sql(os.path.join(BD_TEST_DIR, filename)))
            
    count = 0
    for tbl in tables:
        if tbl["table_name"].lower() in SKIP_TABLES:
            continue
            
        main_code, embed_code, embed_name, is_composite = generate_java(tbl)
        out_file = os.path.join(OUT_DIR, f"{tbl['class_name']}.java")
        
        with open(out_file, "w", encoding='utf-8') as f:
            f.write(main_code)
        count += 1
            
        if is_composite:
            embed_file = os.path.join(OUT_DIR, f"{embed_name}.java")
            with open(embed_file, "w", encoding='utf-8') as f:
                f.write(embed_code)
            count += 1

    print(f"Total generados: {count} archivos Java.")

if __name__ == "__main__":
    main()
