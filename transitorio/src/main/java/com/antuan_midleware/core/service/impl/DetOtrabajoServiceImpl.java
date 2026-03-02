package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.DetOtrabajo;
import com.antuan_midleware.core.repository.DetOtrabajoRepository;
import com.antuan_midleware.core.service.DetOtrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetOtrabajoServiceImpl implements DetOtrabajoService {
    private final DetOtrabajoRepository repository;

    @Override
    public List<DetOtrabajo> findAll() {
        return repository.findAll();
    }
}
