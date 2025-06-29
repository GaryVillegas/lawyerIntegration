package com.integration.lawyer.controller;

import com.integration.lawyer.model.AreaAsesoramiento;
import com.integration.lawyer.service.AreaAsesoramientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/area")
public class AreaAsesoramientoController {

    @Autowired
    private AreaAsesoramientoService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody AreaAsesoramiento area) {
        AreaAsesoramiento guardada = service.save(area);
        return ResponseEntity.status(201).body(guardada);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<AreaAsesoramiento> lista = service.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        AreaAsesoramiento area = service.findById(id);
        if (area == null) {
            return ResponseEntity.status(404).body("Área no encontrada");
        }
        return ResponseEntity.ok(area);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Área eliminada");
    }
}


