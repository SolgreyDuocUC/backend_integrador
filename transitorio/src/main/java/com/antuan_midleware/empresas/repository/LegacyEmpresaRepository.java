package com.antuan_midleware.empresas.repository;

import com.antuan_midleware.empresas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegacyEmpresaRepository extends JpaRepository<Empresa, Long> {
}
