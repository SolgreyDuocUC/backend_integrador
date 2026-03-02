package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetSolicompra;
import com.antuan_midleware.core.model.DetSolicompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetSolicompraRepository extends JpaRepository<DetSolicompra, DetSolicompraId> {
}
