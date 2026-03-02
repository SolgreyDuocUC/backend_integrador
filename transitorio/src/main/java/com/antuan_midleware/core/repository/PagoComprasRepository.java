package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.PagoCompras;
import com.antuan_midleware.core.model.PagoComprasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoComprasRepository extends JpaRepository<PagoCompras, PagoComprasId> {
}
