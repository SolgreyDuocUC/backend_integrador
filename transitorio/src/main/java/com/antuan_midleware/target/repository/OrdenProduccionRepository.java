package com.antuan_midleware.target.repository;

import com.antuan_midleware.target.model.OrdenProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion, Long> {
}
