package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetEvaluacion;
import com.antuan_midleware.core.model.DetEvaluacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetEvaluacionRepository extends JpaRepository<DetEvaluacion, DetEvaluacionId> {
}
