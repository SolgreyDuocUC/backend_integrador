package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Foliocomprobantes;
import com.antuan_midleware.core.model.FoliocomprobantesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoliocomprobantesRepository extends JpaRepository<Foliocomprobantes, FoliocomprobantesId> {
}
