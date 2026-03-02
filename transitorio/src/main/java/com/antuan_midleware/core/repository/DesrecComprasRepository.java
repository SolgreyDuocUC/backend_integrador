package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DesrecCompras;
import com.antuan_midleware.core.model.DesrecComprasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesrecComprasRepository extends JpaRepository<DesrecCompras, DesrecComprasId> {
}
