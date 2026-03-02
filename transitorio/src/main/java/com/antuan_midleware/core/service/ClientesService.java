package com.antuan_midleware.core.service;

import com.antuan_midleware.core.model.Clientes;
import com.antuan_midleware.core.model.ClientesId;
import java.util.List;
import java.util.Optional;

public interface ClientesService {
    List<Clientes> findAll();

    Optional<Clientes> findById(ClientesId id);

    Clientes save(Clientes clientes);

    void deleteById(ClientesId id);
}
