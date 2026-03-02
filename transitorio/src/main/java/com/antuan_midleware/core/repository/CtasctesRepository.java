package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Ctasctes;
import com.antuan_midleware.core.model.CtasctesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CtasctesRepository extends JpaRepository<Ctasctes, CtasctesId> {
}
