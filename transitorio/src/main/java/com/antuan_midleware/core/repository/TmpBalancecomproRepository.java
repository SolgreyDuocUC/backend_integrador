package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TmpBalancecompro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpBalancecomproRepository extends JpaRepository<TmpBalancecompro, Long> {
}
