package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncNvtas;
import com.antuan_midleware.core.model.EncNvtasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncNvtasRepository extends JpaRepository<EncNvtas, EncNvtasId> {
}
