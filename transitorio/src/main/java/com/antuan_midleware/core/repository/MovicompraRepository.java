package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Movicompra;
import com.antuan_midleware.core.model.MovicompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovicompraRepository extends JpaRepository<Movicompra, MovicompraId> {
}
