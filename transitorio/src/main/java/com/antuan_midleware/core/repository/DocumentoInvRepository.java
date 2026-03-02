package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DocumentoInv;
import com.antuan_midleware.core.model.DocumentoInvId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoInvRepository extends JpaRepository<DocumentoInv, DocumentoInvId> {
}
