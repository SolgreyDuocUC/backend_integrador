package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Usuarios;
import com.antuan_midleware.core.model.UsuariosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, UsuariosId> {
}
