package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncCotizacion;
import com.antuan_midleware.core.model.EncCotizacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncCotizacionRepository extends JpaRepository<EncCotizacion, EncCotizacionId> {
}
