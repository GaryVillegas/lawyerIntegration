package com.integration.lawyer.controller;

import com.integration.lawyer.model.Caso;
import com.integration.lawyer.service.CasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CasoController {
    @Autowired
    private CasoService casoService;

    @GetMapping("/caso")
    public ResponseEntity<?> listarCasos(){
        return ResponseEntity.status(200).body(casoService.findAll());
    }
    @GetMapping("/caso/{id}")
    public ResponseEntity<?> buscarCaso(@PathVariable Integer id){
        Caso caso = casoService.findById(id);
        if (caso==null){
            return ResponseEntity.status(404).body("Caso no encontrado");
        }
        return ResponseEntity.status(200).body(caso);
    }
    @DeleteMapping("/caso/{id}")
    public ResponseEntity<?> borrarcaso(@PathVariable Integer id){
        casoService.delete(id);
        return ResponseEntity.status(200).body("caso eliminado");
    }
    @PostMapping("/caso")
    public ResponseEntity<?> guardarCaso(@RequestBody Caso caso){
        Caso casoGuardado = casoService.save(caso);
        return ResponseEntity.status(201).body(casoGuardado);
    }
}
