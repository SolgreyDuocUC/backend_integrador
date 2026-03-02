package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Pasocomprobante;
import com.antuan_midleware.core.model.PasocomprobanteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasocomprobanteRepository extends JpaRepository<Pasocomprobante, PasocomprobanteId> {
}
