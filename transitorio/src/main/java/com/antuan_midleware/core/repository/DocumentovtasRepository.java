package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Documentovtas;
import com.antuan_midleware.core.model.DocumentovtasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentovtasRepository extends JpaRepository<Documentovtas, DocumentovtasId> {
}
