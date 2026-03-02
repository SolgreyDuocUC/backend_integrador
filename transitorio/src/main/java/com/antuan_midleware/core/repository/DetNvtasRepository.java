package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetNvtas;
import com.antuan_midleware.core.model.DetNvtasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetNvtasRepository extends JpaRepository<DetNvtas, DetNvtasId> {
}
