package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Alarmas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmasRepository extends JpaRepository<Alarmas, Long> {
}
