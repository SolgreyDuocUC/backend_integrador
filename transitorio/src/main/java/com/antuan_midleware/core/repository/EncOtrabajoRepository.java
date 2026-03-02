package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncOtrabajo;
import com.antuan_midleware.core.model.EncOtrabajoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncOtrabajoRepository extends JpaRepository<EncOtrabajo, EncOtrabajoId> {
}
