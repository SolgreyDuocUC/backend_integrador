package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Folios;
import com.antuan_midleware.core.model.FoliosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoliosRepository extends JpaRepository<Folios, FoliosId> {
}
