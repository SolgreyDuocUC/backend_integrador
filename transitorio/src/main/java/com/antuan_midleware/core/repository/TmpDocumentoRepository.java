package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TmpDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpDocumentoRepository extends JpaRepository<TmpDocumento, Long> {
}
