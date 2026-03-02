package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Presupestresultado;
import com.antuan_midleware.core.model.PresupestresultadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresupestresultadoRepository extends JpaRepository<Presupestresultado, PresupestresultadoId> {
}
