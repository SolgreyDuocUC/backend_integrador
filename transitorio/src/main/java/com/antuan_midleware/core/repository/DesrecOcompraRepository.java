package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DesrecOcompra;
import com.antuan_midleware.core.model.DesrecOcompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesrecOcompraRepository extends JpaRepository<DesrecOcompra, DesrecOcompraId> {
}
