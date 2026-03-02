package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TmpCtaficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpCtafichaRepository extends JpaRepository<TmpCtaficha, Long> {
}
