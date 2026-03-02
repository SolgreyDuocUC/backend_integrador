package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Plantillas;
import com.antuan_midleware.core.model.PlantillasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantillasRepository extends JpaRepository<Plantillas, PlantillasId> {
}
