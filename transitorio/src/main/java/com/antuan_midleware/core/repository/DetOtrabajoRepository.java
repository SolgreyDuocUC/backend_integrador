package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetOtrabajo;
import com.antuan_midleware.core.model.DetOtrabajoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetOtrabajoRepository extends JpaRepository<DetOtrabajo, DetOtrabajoId> {
}
