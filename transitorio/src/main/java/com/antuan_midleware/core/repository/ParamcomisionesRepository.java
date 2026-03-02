package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Paramcomisiones;
import com.antuan_midleware.core.model.ParamcomisionesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamcomisionesRepository extends JpaRepository<Paramcomisiones, ParamcomisionesId> {
}
