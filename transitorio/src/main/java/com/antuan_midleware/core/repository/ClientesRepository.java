package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Clientes;
import com.antuan_midleware.core.model.ClientesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, ClientesId> {
}
