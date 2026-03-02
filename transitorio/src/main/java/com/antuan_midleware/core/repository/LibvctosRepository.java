package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Libvctos;
import com.antuan_midleware.core.model.LibvctosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibvctosRepository extends JpaRepository<Libvctos, LibvctosId> {
}
