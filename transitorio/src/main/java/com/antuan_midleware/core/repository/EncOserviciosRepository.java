package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncOservicios;
import com.antuan_midleware.core.model.EncOserviciosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncOserviciosRepository extends JpaRepository<EncOservicios, EncOserviciosId> {
}
