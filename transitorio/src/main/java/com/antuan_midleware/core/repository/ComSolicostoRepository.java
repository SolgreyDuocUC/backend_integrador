package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.ComSolicosto;
import com.antuan_midleware.core.model.ComSolicostoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComSolicostoRepository extends JpaRepository<ComSolicosto, ComSolicostoId> {
}
