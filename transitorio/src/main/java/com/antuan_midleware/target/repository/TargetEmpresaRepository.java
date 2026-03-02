package com.antuan_midleware.target.repository;

import com.antuan_midleware.target.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetEmpresaRepository extends JpaRepository<Empresa, String> {
}
