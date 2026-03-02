package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Ocrecuperada;
import com.antuan_midleware.core.model.OcrecuperadaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcrecuperadaRepository extends JpaRepository<Ocrecuperada, OcrecuperadaId> {
}
