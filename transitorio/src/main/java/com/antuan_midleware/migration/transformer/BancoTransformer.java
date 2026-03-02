package com.antuan_midleware.migration.transformer;

import com.antuan_midleware.core.model.Bancos;
import com.antuan_midleware.target.model.Banco;
import org.springframework.stereotype.Component;

@Component
public class BancoTransformer {

    public Banco transform(Bancos legacy) {
        if (legacy == null)
            return null;

        Banco target = new Banco();
        target.setNombre(legacy.getNombre());
        target.setCodigo(legacy.getCodigo());
        target.setEmpresaOriginal(legacy.getEmpresa());
        return target;
    }
}
