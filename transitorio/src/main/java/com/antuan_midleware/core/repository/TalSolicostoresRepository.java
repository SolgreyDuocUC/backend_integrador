package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TalSolicostores;
import com.antuan_midleware.core.model.TalSolicostoresId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalSolicostoresRepository extends JpaRepository<TalSolicostores, TalSolicostoresId> {
}
