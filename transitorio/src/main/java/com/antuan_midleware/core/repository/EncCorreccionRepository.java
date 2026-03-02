package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncCorreccion;
import com.antuan_midleware.core.model.EncCorreccionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncCorreccionRepository extends JpaRepository<EncCorreccion, EncCorreccionId> {
}
