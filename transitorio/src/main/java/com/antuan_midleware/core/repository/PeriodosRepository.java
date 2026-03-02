package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Periodos;
import com.antuan_midleware.core.model.PeriodosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodosRepository extends JpaRepository<Periodos, PeriodosId> {
}
