package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.TalOcompra;
import com.antuan_midleware.core.model.TalOcompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalOcompraRepository extends JpaRepository<TalOcompra, TalOcompraId> {
}
