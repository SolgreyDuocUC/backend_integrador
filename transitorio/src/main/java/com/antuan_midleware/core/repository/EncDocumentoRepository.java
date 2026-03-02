package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncDocumento;
import com.antuan_midleware.core.model.EncDocumentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncDocumentoRepository extends JpaRepository<EncDocumento, EncDocumentoId> {
}
