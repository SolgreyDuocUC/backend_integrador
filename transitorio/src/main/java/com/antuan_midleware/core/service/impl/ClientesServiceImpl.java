package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.Clientes;
import com.antuan_midleware.core.model.ClientesId;
import com.antuan_midleware.core.repository.ClientesRepository;
import com.antuan_midleware.core.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;

    @Override
    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    public Optional<Clientes> findById(ClientesId id) {
        return clientesRepository.findById(id);
    }

    @Override
    public Clientes save(Clientes clientes) {
        return clientesRepository.save(clientes);
    }

    @Override
    public void deleteById(ClientesId id) {
        clientesRepository.deleteById(id);
    }
}
