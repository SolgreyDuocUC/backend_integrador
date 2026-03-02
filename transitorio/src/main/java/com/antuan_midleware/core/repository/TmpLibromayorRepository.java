package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TmpLibromayor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpLibromayorRepository extends JpaRepository<TmpLibromayor, Long> {
}
