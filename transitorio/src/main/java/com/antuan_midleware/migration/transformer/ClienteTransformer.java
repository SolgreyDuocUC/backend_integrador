package com.antuan_midleware.migration.transformer;

import com.antuan_midleware.core.model.Clientes;
import com.antuan_midleware.target.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteTransformer {

    public Cliente transform(Clientes legacyCliente) {
        if (legacyCliente == null) {
            return null;
        }

        Cliente target = new Cliente();

        // Mapeo básico
        target.setNombre(legacyCliente.getRazonSocial());
        target.setApellido(""); // El legacy no separa nombre/apellido para Clientes (Empresas)
        target.setRun(normalizeRut(legacyCliente.getRutComprador() != null ? legacyCliente.getRutComprador() : ""));
        target.setCorreo(legacyCliente.getCorreo());
        target.setTelefono(legacyCliente.getTelefono());
        target.setActivo(true);

        return target;
    }

    private String normalizeRut(String rut) {
        if (rut == null)
            return "";
        return rut.replace(".", "").replace("-", "").toUpperCase().trim();
    }
}
