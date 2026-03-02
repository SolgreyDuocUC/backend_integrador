package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Gastos;
import com.antuan_midleware.core.model.GastosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, GastosId> {
}
