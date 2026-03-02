package com.antuan_midleware.core.service;

import com.antuan_midleware.core.model.Proveedores;
import com.antuan_midleware.core.model.ProveedoresId;
import java.util.List;
import java.util.Optional;

public interface ProveedoresService {
    List<Proveedores> findAll();

    Optional<Proveedores> findById(ProveedoresId id);

    Proveedores save(Proveedores proveedores);

    void deleteById(ProveedoresId id);
}
