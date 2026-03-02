package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncCompras;
import com.antuan_midleware.core.model.EncComprasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncComprasRepository extends JpaRepository<EncCompras, EncComprasId> {
}
