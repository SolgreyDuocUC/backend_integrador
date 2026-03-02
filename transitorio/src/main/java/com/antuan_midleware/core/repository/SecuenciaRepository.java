package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Secuencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuenciaRepository extends JpaRepository<Secuencia, Long> {
}
