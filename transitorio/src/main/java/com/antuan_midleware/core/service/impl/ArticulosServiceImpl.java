package com.antuan_midleware.core.service.impl;

import com.antuan_midleware.core.model.Articulos;
import com.antuan_midleware.core.model.ArticulosId;
import com.antuan_midleware.core.repository.ArticulosRepository;
import com.antuan_midleware.core.service.ArticulosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticulosServiceImpl implements ArticulosService {

    private final ArticulosRepository articulosRepository;

    @Override
    public List<Articulos> findAll() {
        return articulosRepository.findAll();
    }

    @Override
    public Optional<Articulos> findById(ArticulosId id) {
        return articulosRepository.findById(id);
    }

    @Override
    public Articulos save(Articulos articulos) {
        return articulosRepository.save(articulos);
    }

    @Override
    public void deleteById(ArticulosId id) {
        articulosRepository.deleteById(id);
    }
}
