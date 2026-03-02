package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.ConfiguracionSistema;
import com.antuan_midleware.core.model.ConfiguracionSistemaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionSistemaRepository extends JpaRepository<ConfiguracionSistema, ConfiguracionSistemaId> {
}
