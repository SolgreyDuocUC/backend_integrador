package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.PagoNvtas;
import com.antuan_midleware.core.model.PagoNvtasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoNvtasRepository extends JpaRepository<PagoNvtas, PagoNvtasId> {
}
