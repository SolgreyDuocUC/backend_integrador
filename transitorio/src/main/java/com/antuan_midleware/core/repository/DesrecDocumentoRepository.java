package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DesrecDocumento;
import com.antuan_midleware.core.model.DesrecDocumentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesrecDocumentoRepository extends JpaRepository<DesrecDocumento, DesrecDocumentoId> {
}
