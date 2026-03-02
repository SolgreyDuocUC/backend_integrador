package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.PagoDocumento;
import com.antuan_midleware.core.model.PagoDocumentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoDocumentoRepository extends JpaRepository<PagoDocumento, PagoDocumentoId> {
}
