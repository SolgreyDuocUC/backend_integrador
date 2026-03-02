package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.Bancos;
import com.antuan_midleware.core.repository.BancosRepository;
import com.antuan_midleware.core.service.BancosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BancosServiceImpl implements BancosService {
    private final BancosRepository repository;

    @Override
    public List<Bancos> findAll() {
        return repository.findAll();
    }
}
