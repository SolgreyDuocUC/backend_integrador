package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncOcompra;
import com.antuan_midleware.core.model.EncOcompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncOcompraRepository extends JpaRepository<EncOcompra, EncOcompraId> {
}
