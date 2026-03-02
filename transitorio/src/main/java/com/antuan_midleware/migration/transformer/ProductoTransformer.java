package com.antuan_midleware.migration.transformer;

import com.antuan_midleware.core.model.Articulos;
import com.antuan_midleware.target.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoTransformer {

    public Producto transform(Articulos legacyArticulo) {
        if (legacyArticulo == null) {
            return null;
        }

        Producto target = new Producto();

        target.setCodigo(legacyArticulo.getId().getCodigo());
        target.setNombre(legacyArticulo.getDescripcion());
        target.setDescripcion(legacyArticulo.getComentario());
        target.setActivo(legacyArticulo.getActivo() != null ? legacyArticulo.getActivo() : true);

        // Otros campos como genero/color podrían extraerse de la descripción si hubiera
        // lógica específica
        target.setGenero(null);
        target.setColor(null);

        return target;
    }
}
