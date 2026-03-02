package com.antuan_midleware.target.repository;

import com.antuan_midleware.target.model.MigrationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MigrationLogRepository extends JpaRepository<MigrationLog, Long> {
}
