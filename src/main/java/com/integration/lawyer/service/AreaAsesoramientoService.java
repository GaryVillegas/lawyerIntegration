package com.integration.lawyer.service;

import com.integration.lawyer.model.AreaAsesoramiento;
import com.integration.lawyer.repository.AreaAsesoramientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaAsesoramientoService {

    @Autowired
    private AreaAsesoramientoRepository repository;

    public List<AreaAsesoramiento> findAll() {
        return repository.findAll();
    }

    public AreaAsesoramiento save(AreaAsesoramiento area) {
        return repository.save(area);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public AreaAsesoramiento findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}

