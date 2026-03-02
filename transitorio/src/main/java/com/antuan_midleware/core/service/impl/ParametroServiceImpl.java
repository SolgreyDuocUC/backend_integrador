package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.service.ParametroService;
import com.antuan_midleware.empresas.model.Parametro;
import com.antuan_midleware.empresas.model.ParametroId;
import com.antuan_midleware.empresas.repository.ParametroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParametroServiceImpl implements ParametroService {

    private final ParametroRepository parametroRepository;

    @Override
    public List<Parametro> findAll() {
        return parametroRepository.findAll();
    }

    @Override
    public Optional<Parametro> findById(ParametroId id) {
        return parametroRepository.findById(id);
    }

    @Override
    public Parametro save(Parametro parametro) {
        return parametroRepository.save(parametro);
    }

    @Override
    public void deleteById(ParametroId id) {
        parametroRepository.deleteById(id);
    }
}
