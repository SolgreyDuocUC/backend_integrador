package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Moviconta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovicontaRepository extends JpaRepository<Moviconta, Long> {
}
