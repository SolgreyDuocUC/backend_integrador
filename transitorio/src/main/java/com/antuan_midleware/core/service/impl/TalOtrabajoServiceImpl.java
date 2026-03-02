package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.TalOtrabajo;
import com.antuan_midleware.core.repository.TalOtrabajoRepository;
import com.antuan_midleware.core.service.TalOtrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TalOtrabajoServiceImpl implements TalOtrabajoService {
    private final TalOtrabajoRepository repository;

    @Override
    public List<TalOtrabajo> findAll() {
        return repository.findAll();
    }
}
