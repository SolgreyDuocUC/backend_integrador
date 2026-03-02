package com.antuan_midleware.core.service;

import com.antuan_midleware.empresas.model.Parametro;
import com.antuan_midleware.empresas.model.ParametroId;
import java.util.List;
import java.util.Optional;

public interface ParametroService {
    List<Parametro> findAll();

    Optional<Parametro> findById(ParametroId id);

    Parametro save(Parametro parametro);

    void deleteById(ParametroId id);
}
