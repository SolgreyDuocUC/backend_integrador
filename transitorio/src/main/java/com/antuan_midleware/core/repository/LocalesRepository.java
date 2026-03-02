package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Locales;
import com.antuan_midleware.core.model.LocalesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalesRepository extends JpaRepository<Locales, LocalesId> {
}
