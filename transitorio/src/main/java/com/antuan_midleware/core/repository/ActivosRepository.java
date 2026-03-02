package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Activos;
import com.antuan_midleware.core.model.ActivosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivosRepository extends JpaRepository<Activos, ActivosId> {
}
