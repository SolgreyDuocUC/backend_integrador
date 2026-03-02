package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Defcentracompra;
import com.antuan_midleware.core.model.DefcentracompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefcentracompraRepository extends JpaRepository<Defcentracompra, DefcentracompraId> {
}
