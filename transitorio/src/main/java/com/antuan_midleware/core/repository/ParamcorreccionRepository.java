package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Paramcorreccion;
import com.antuan_midleware.core.model.ParamcorreccionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamcorreccionRepository extends JpaRepository<Paramcorreccion, ParamcorreccionId> {
}
