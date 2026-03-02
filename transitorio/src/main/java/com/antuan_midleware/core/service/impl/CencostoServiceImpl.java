package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.Cencosto;
import com.antuan_midleware.core.repository.CencostoRepository;
import com.antuan_midleware.core.service.CencostoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CencostoServiceImpl implements CencostoService {
    private final CencostoRepository repository;

    @Override
    public List<Cencosto> findAll() {
        return repository.findAll();
    }
}
