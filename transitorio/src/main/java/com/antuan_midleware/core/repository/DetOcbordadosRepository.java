package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.DetOcbordados;
import com.antuan_midleware.core.model.DetOcbordadosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetOcbordadosRepository extends JpaRepository<DetOcbordados, DetOcbordadosId> {
}
