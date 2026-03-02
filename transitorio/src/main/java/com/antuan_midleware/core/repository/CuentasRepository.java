package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Cuentas;
import com.antuan_midleware.core.model.CuentasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentasRepository extends JpaRepository<Cuentas, CuentasId> {
}
