package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DesrecGuias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesrecGuiasRepository extends JpaRepository<DesrecGuias, Long> {
}
