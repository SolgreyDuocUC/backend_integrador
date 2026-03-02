package com.antuan_midleware.target.repository;

import com.antuan_midleware.target.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long> {
    Optional<Comuna> findByNombre(String nombre);
}
