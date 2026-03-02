package com.antuan_midleware.migration.transformer;

import com.antuan_midleware.core.model.Proveedores;
import com.antuan_midleware.target.model.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorTransformer {

    public Proveedor transform(Proveedores legacyProveedor) {
        if (legacyProveedor == null) {
            return null;
        }

        Proveedor target = new Proveedor();

        target.setNombre(legacyProveedor.getRazonSocial());
        target.setRut(normalizeRut(legacyProveedor.getSigla())); // Usando Sigla o Rut si estuviera disponible claro
        target.setDireccion(legacyProveedor.getDireccion());
        target.setTelefono(legacyProveedor.getTelefono());
        target.setContacto(legacyProveedor.getContacto());
        target.setActivo(true);

        return target;
    }

    private String normalizeRut(String rut) {
        if (rut == null)
            return "";
        return rut.replace(".", "").replace("-", "").toUpperCase().trim();
    }
}
