package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.Proveedores;
import com.antuan_midleware.core.model.ProveedoresId;
import com.antuan_midleware.core.repository.ProveedoresRepository;
import com.antuan_midleware.core.service.ProveedoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedoresServiceImpl implements ProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

    @Override
    public List<Proveedores> findAll() {
        return proveedoresRepository.findAll();
    }

    @Override
    public Optional<Proveedores> findById(ProveedoresId id) {
        return proveedoresRepository.findById(id);
    }

    @Override
    public Proveedores save(Proveedores proveedores) {
        return proveedoresRepository.save(proveedores);
    }

    @Override
    public void deleteById(ProveedoresId id) {
        proveedoresRepository.deleteById(id);
    }
}
