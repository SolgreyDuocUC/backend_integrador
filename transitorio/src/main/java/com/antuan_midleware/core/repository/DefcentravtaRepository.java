package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Defcentravta;
import com.antuan_midleware.core.model.DefcentravtaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefcentravtaRepository extends JpaRepository<Defcentravta, DefcentravtaId> {
}
