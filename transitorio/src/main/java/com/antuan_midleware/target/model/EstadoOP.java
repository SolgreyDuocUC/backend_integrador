package com.antuan_midleware.target.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Replicando el Enum del dominio del nuevo backend para la persistencia
@Getter
@RequiredArgsConstructor
public enum EstadoOP {
    PENDIENTE("Pendiente de Inicio"),
    EN_PROCESO("En Proceso"),
    DETENIDA("Detenida"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada");

    private final String descripcion;
}
