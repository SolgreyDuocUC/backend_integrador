package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Libcheques;
import com.antuan_midleware.core.model.LibchequesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibchequesRepository extends JpaRepository<Libcheques, LibchequesId> {
}
