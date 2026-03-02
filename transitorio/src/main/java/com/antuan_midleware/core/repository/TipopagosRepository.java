package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Tipopagos;
import com.antuan_midleware.core.model.TipopagosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipopagosRepository extends JpaRepository<Tipopagos, TipopagosId> {
}
