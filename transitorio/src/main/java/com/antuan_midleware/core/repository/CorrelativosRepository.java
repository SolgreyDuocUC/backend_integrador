package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Correlativos;
import com.antuan_midleware.core.model.CorrelativosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrelativosRepository extends JpaRepository<Correlativos, CorrelativosId> {
}
