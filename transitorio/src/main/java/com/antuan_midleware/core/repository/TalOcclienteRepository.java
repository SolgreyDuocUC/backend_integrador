package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TalOccliente;
import com.antuan_midleware.core.model.TalOcclienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalOcclienteRepository extends JpaRepository<TalOccliente, TalOcclienteId> {
}
