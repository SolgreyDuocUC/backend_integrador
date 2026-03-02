package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.MedidasAlternativas;
import com.antuan_midleware.core.model.MedidasAlternativasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidasAlternativasRepository extends JpaRepository<MedidasAlternativas, MedidasAlternativasId> {
}
