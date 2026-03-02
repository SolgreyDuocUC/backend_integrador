package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EntOservicios;
import com.antuan_midleware.core.model.EntOserviciosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntOserviciosRepository extends JpaRepository<EntOservicios, EntOserviciosId> {
}
