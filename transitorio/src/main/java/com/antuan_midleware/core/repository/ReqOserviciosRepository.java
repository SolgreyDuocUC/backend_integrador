package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.ReqOservicios;
import com.antuan_midleware.core.model.ReqOserviciosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqOserviciosRepository extends JpaRepository<ReqOservicios, ReqOserviciosId> {
}
