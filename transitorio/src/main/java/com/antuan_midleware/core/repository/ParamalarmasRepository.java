package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Paramalarmas;
import com.antuan_midleware.core.model.ParamalarmasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamalarmasRepository extends JpaRepository<Paramalarmas, ParamalarmasId> {
}
