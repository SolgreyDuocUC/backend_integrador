package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.MatOservicios;
import com.antuan_midleware.core.model.MatOserviciosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatOserviciosRepository extends JpaRepository<MatOservicios, MatOserviciosId> {
}
