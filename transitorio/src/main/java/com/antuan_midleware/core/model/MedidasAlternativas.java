package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medidas_alternativas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedidasAlternativas implements Serializable {

    @EmbeddedId
    private MedidasAlternativasId id;

    @Column(name = "Factor")
    private Double factor;

}
