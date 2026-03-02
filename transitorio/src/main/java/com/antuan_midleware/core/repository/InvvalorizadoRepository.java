package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Invvalorizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvvalorizadoRepository extends JpaRepository<Invvalorizado, Long> {
}
