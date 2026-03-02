package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TalOtrabajo;
import com.antuan_midleware.core.model.TalOtrabajoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalOtrabajoRepository extends JpaRepository<TalOtrabajo, TalOtrabajoId> {
}
