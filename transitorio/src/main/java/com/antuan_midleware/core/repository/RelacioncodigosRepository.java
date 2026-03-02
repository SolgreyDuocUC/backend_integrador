package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Relacioncodigos;
import com.antuan_midleware.core.model.RelacioncodigosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacioncodigosRepository extends JpaRepository<Relacioncodigos, RelacioncodigosId> {
}
