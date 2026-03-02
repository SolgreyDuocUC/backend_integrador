package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetCorreccion;
import com.antuan_midleware.core.model.DetCorreccionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetCorreccionRepository extends JpaRepository<DetCorreccion, DetCorreccionId> {
}
