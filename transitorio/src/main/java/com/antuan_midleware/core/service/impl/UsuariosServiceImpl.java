package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.Usuarios;
import com.antuan_midleware.core.repository.UsuariosRepository;
import com.antuan_midleware.core.service.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements UsuariosService {
    private final UsuariosRepository repository;

    @Override
    public List<Usuarios> findAll() {
        return repository.findAll();
    }
}
