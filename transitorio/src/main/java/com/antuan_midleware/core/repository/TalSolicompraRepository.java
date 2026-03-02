package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TalSolicompra;
import com.antuan_midleware.core.model.TalSolicompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalSolicompraRepository extends JpaRepository<TalSolicompra, TalSolicompraId> {
}
