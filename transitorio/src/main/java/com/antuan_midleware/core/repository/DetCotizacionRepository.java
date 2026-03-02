package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetCotizacion;
import com.antuan_midleware.core.model.DetCotizacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetCotizacionRepository extends JpaRepository<DetCotizacion, DetCotizacionId> {
}
