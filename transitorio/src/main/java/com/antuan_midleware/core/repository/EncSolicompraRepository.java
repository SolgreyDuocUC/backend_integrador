package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncSolicompra;
import com.antuan_midleware.core.model.EncSolicompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncSolicompraRepository extends JpaRepository<EncSolicompra, EncSolicompraId> {
}
