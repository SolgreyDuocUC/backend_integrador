package com.antuan_midleware.migration.validation;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MigrationValidator {

    public List<String> validateRut(String rut) {
        List<String> errors = new ArrayList<>();
        if (rut == null || rut.trim().isEmpty()) {
            errors.add("RUT is mandatory");
            return errors;
        }

        String cleanRut = rut.replace(".", "").replace("-", "").toUpperCase().trim();
        if (cleanRut.length() < 8 || cleanRut.length() > 10) {
            errors.add("RUT has invalid length: " + rut);
        }

        // Aquí se podría añadir validación de dígito verificador si es necesario

        return errors;
    }

    public List<String> validateRequired(String fieldName, Object value) {
        List<String> errors = new ArrayList<>();
        if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
            errors.add("Field '" + fieldName + "' is mandatory");
        }
        return errors;
    }
}
