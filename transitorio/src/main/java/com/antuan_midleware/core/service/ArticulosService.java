package com.antuan_midleware.core.service;

import com.antuan_midleware.core.model.Articulos;
import com.antuan_midleware.core.model.ArticulosId;
import java.util.List;
import java.util.Optional;

public interface ArticulosService {
    List<Articulos> findAll();

    Optional<Articulos> findById(ArticulosId id);

    Articulos save(Articulos articulos);

    void deleteById(ArticulosId id);
}
