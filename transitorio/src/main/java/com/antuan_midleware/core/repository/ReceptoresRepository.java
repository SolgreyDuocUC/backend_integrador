package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Receptores;
import com.antuan_midleware.core.model.ReceptoresId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptoresRepository extends JpaRepository<Receptores, ReceptoresId> {
}
