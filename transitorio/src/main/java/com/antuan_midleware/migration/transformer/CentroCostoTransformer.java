package com.antuan_midleware.migration.transformer;

import com.antuan_midleware.core.model.Cencosto;
import com.antuan_midleware.target.model.CentroCosto;
import org.springframework.stereotype.Component;

@Component
public class CentroCostoTransformer {

    public CentroCosto transform(Cencosto legacy) {
        if (legacy == null)
            return null;

        CentroCosto target = new CentroCosto();
        if (legacy.getId() != null) {
            target.setCodigoOriginal(String.valueOf(legacy.getId().getCodigo()));
        }
        target.setDescripcion(legacy.getDescripcion());
        return target;
    }
}
