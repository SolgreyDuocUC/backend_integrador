package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Corrcomprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrcomprobanteRepository extends JpaRepository<Corrcomprobante, Long> {
}
