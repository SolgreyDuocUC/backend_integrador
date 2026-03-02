package com.antuan_midleware.core.service;

import com.antuan_midleware.empresas.model.Empresa;
import java.util.List;
import java.util.Optional;

public interface EmpresaService {
    List<Empresa> findAll();

    Optional<Empresa> findById(String id);

    Empresa save(Empresa empresa);

    void deleteById(String id);
}
