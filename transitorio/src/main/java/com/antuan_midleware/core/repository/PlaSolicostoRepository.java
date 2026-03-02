package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.PlaSolicosto;
import com.antuan_midleware.core.model.PlaSolicostoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaSolicostoRepository extends JpaRepository<PlaSolicosto, PlaSolicostoId> {
}
