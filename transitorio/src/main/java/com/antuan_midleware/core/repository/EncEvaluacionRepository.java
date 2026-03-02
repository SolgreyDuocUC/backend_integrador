package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncEvaluacion;
import com.antuan_midleware.core.model.EncEvaluacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncEvaluacionRepository extends JpaRepository<EncEvaluacion, EncEvaluacionId> {
}
