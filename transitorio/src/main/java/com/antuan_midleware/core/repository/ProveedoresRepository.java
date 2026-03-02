package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Proveedores;
import com.antuan_midleware.core.model.ProveedoresId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, ProveedoresId> {
}
