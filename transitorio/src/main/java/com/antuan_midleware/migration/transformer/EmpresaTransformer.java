package com.antuan_midleware.migration.transformer;

import com.antuan_midleware.empresas.model.Empresa;
// Remove aliased import, use fully qualified name in code
import org.springframework.stereotype.Component;

@Component
public class EmpresaTransformer {

    public com.antuan_midleware.target.model.Empresa transform(Empresa legacyEmpresa) {
        if (legacyEmpresa == null) {
            return null;
        }

        com.antuan_midleware.target.model.Empresa target = new com.antuan_midleware.target.model.Empresa();

        // Mapeo de campos según ABOUT_PROJECT.md y estructura Target
        target.setRut(normalizeRut(legacyEmpresa.getRut()));
        target.setRazonSocial(legacyEmpresa.getRazonSocial());
        target.setActivo(legacyEmpresa.getActiva() != null ? legacyEmpresa.getActiva() : true);

        // Campos que no existen en Legacy pero sí en ERP se dejan por defecto o nulos
        // por ahora
        target.setCorreo(null);
        target.setTelefono(null);

        return target;
    }

    private String normalizeRut(String rut) {
        if (rut == null)
            return null;
        // Limpieza básica de puntos y guiones si fuera necesario
        return rut.replace(".", "").replace("-", "").toUpperCase().trim();
    }
}
