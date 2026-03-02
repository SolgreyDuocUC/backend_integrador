package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncInvfisico;
import com.antuan_midleware.core.model.EncInvfisicoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncInvfisicoRepository extends JpaRepository<EncInvfisico, EncInvfisicoId> {
}
