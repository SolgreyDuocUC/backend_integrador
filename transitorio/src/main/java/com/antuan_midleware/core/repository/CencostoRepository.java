package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Cencosto;
import com.antuan_midleware.core.model.CencostoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CencostoRepository extends JpaRepository<Cencosto, CencostoId> {
}
