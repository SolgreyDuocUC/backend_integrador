package com.antuan_midleware.target.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "migration_logs")
@Data
@NoArgsConstructor
public class MigrationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entityName;

    @Column(nullable = false)
    private String status; // SUCCESS, FAILURE, PARTIAL

    private Integer recordsProcessed;
    private Integer recordsSuccess;
    private Integer recordsFailed;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    @Column(nullable = false)
    private LocalDateTime executionTime;

    public MigrationLog(String entityName) {
        this.entityName = entityName;
        this.executionTime = LocalDateTime.now();
        this.status = "STARTED";
    }
}
