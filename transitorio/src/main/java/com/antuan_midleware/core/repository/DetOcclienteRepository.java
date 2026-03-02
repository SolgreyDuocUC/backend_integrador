package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetOccliente;
import com.antuan_midleware.core.model.DetOcclienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetOcclienteRepository extends JpaRepository<DetOccliente, DetOcclienteId> {
}
