package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TmpSaldos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpSaldosRepository extends JpaRepository<TmpSaldos, Long> {
}
