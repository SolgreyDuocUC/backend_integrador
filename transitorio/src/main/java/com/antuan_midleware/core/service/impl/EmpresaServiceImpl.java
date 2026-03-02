package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.service.EmpresaService;
import com.antuan_midleware.empresas.model.Empresa;
import com.antuan_midleware.empresas.repository.LegacyEmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final LegacyEmpresaRepository empresaRepository;

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Optional<Empresa> findById(String id) {
        return empresaRepository.findById(Long.valueOf(id));
    }

    @Override
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public void deleteById(String id) {
        empresaRepository.deleteById(Long.valueOf(id));
    }
}
