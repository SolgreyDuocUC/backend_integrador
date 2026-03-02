package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TmpBalancegeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpBalancegeneralRepository extends JpaRepository<TmpBalancegeneral, Long> {
}
