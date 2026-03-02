package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetCompras;
import com.antuan_midleware.core.model.DetComprasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetComprasRepository extends JpaRepository<DetCompras, DetComprasId> {
}
