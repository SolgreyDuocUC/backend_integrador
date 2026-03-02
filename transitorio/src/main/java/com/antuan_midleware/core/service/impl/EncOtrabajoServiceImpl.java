package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.EncOtrabajo;
import com.antuan_midleware.core.repository.EncOtrabajoRepository;
import com.antuan_midleware.core.service.EncOtrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EncOtrabajoServiceImpl implements EncOtrabajoService {
    private final EncOtrabajoRepository repository;

    @Override
    public List<EncOtrabajo> findAll() {
        return repository.findAll();
    }
}
