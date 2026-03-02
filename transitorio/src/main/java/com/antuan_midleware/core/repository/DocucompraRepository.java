package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Docucompra;
import com.antuan_midleware.core.model.DocucompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocucompraRepository extends JpaRepository<Docucompra, DocucompraId> {
}
