import os

MODEL_DIR = r"C:\Users\Ejecutivo2.ANTUAN\Documents\backend_transitorio\transitorio\src\main\java\com\antuan_midleware\core\model"
IMPORT_TO_REMOVE = "import jakarta.persistence.Embeddable;"
ANNOTATION_TO_KEEP = "@Embeddable"

def clean_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    
    content = "".join(lines)
    
    # Check if the import exists and the annotation DOES NOT exist
    if IMPORT_TO_REMOVE in content and ANNOTATION_TO_KEEP not in content:
        print(f"Cleaning {os.path.basename(file_path)}...")
        new_lines = [line for line in lines if line.strip() != IMPORT_TO_REMOVE]
        
        with open(file_path, 'w', encoding='utf-8') as f:
            f.writelines(new_lines)
        return True
    return False

def main():
    count = 0
    for root, dirs, files in os.walk(MODEL_DIR):
        for file in files:
            if file.endswith(".java"):
                if clean_file(os.path.join(root, file)):
                    count += 1
    print(f"Finished. Cleaned {count} files.")

if __name__ == "__main__":
    main()
