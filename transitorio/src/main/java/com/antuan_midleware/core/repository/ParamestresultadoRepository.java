package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Paramestresultado;
import com.antuan_midleware.core.model.ParamestresultadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamestresultadoRepository extends JpaRepository<Paramestresultado, ParamestresultadoId> {
}
