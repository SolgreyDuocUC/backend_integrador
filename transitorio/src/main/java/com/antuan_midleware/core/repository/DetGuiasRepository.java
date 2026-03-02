package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetGuias;
import com.antuan_midleware.core.model.DetGuiasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetGuiasRepository extends JpaRepository<DetGuias, DetGuiasId> {
}
