package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Articulos;
import com.antuan_midleware.core.model.ArticulosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticulosRepository extends JpaRepository<Articulos, ArticulosId> {
}
