package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.EncOccliente;
import com.antuan_midleware.core.model.EncOcclienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncOcclienteRepository extends JpaRepository<EncOccliente, EncOcclienteId> {
}
