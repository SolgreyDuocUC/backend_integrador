package com.antuan_midleware.target.repository;

import com.antuan_midleware.target.model.CentroCosto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroCostoRepository extends JpaRepository<CentroCosto, Long> {
}
