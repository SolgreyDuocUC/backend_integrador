package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DesrecNvtas;
import com.antuan_midleware.core.model.DesrecNvtasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesrecNvtasRepository extends JpaRepository<DesrecNvtas, DesrecNvtasId> {
}
