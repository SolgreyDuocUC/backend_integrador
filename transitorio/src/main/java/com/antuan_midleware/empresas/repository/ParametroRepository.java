package com.antuan_midleware.empresas.repository;

import com.antuan_midleware.empresas.model.Parametro;
import com.antuan_midleware.empresas.model.ParametroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, ParametroId> {
}
