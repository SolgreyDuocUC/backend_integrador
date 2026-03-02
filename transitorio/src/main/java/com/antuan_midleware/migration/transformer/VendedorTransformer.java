package com.antuan_midleware.migration.transformer;

import com.antuan_midleware.core.model.Usuarios;
import com.antuan_midleware.target.model.Vendedor;
import org.springframework.stereotype.Component;

@Component
public class VendedorTransformer {

    public Vendedor transform(Usuarios legacy) {
        if (legacy == null)
            return null;

        Vendedor target = new Vendedor();
        target.setNombre(legacy.getNombreUsuario());
        if (legacy.getId() != null) {
            target.setRun(legacy.getId().getRutUsuario());
        }
        // usuarioId se asignará en el loader si es necesario vincular con User entity
        return target;
    }
}
