package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Accesos;
import com.antuan_midleware.core.model.AccesosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesosRepository extends JpaRepository<Accesos, AccesosId> {
}
