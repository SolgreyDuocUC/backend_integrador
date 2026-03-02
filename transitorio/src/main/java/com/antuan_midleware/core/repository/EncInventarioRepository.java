package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncInventario;
import com.antuan_midleware.core.model.EncInventarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncInventarioRepository extends JpaRepository<EncInventario, EncInventarioId> {
}
