package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.PagoGuias;
import com.antuan_midleware.core.model.PagoGuiasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoGuiasRepository extends JpaRepository<PagoGuias, PagoGuiasId> {
}
