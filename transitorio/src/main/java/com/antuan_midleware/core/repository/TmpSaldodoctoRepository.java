package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TmpSaldodocto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpSaldodoctoRepository extends JpaRepository<TmpSaldodocto, Long> {
}
