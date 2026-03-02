package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Preciofamilia;
import com.antuan_midleware.core.model.PreciofamiliaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreciofamiliaRepository extends JpaRepository<Preciofamilia, PreciofamiliaId> {
}
