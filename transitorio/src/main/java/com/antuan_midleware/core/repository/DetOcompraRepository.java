package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetOcompra;
import com.antuan_midleware.core.model.DetOcompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetOcompraRepository extends JpaRepository<DetOcompra, DetOcompraId> {
}
