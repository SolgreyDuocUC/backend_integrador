package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Configuracuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracuentaRepository extends JpaRepository<Configuracuenta, Long> {
}
