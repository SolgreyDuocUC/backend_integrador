package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncGuias;
import com.antuan_midleware.core.model.EncGuiasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncGuiasRepository extends JpaRepository<EncGuias, EncGuiasId> {
}
