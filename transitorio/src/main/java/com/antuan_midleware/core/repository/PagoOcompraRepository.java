package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.PagoOcompra;
import com.antuan_midleware.core.model.PagoOcompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoOcompraRepository extends JpaRepository<PagoOcompra, PagoOcompraId> {
}
