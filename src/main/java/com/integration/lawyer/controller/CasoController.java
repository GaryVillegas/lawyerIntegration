package com.integration.lawyer.controller;

import com.integration.lawyer.model.Caso;
import com.integration.lawyer.model.Usuario;
import com.integration.lawyer.service.CasoService;
import com.integration.lawyer.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CasoController {
    @Autowired
    private CasoService casoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/caso")
    public ResponseEntity<?> listarCasos(){
        if(casoService.findAll().isEmpty()){
            return ResponseEntity.status(201).body("No hay casos disponibles");
        }
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

    //Crear un caso
    @PostMapping("/caso/nuevo")
    public ResponseEntity<?> guardarCaso(@RequestBody Caso caso) {
        // Validar si el objeto Caso o su usuario son nulos
        if (caso == null || caso.getUsuario() == null || caso.getUsuario().getId() == null) {
            return ResponseEntity.status(400).body("El caso o el usuario asociado no es válido");
        }

        // Buscar el usuario en la base de datos por su ID
        Usuario usuario = usuarioService.findById(caso.getUsuario().getId());
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado con ID: " + caso.getUsuario().getId());
        }

        // Asociar el usuario existente al caso
        caso.setUsuario(usuario);

        // Guardar el caso
        Caso casoGuardado = casoService.save(caso);
        return ResponseEntity.status(201).body(casoGuardado);
    }



    //Actualizar un caso
    @PutMapping("/caso/editar/{id}")
    public ResponseEntity<?> actualizarCaso(@PathVariable Integer id, @RequestBody Caso casoNuevo){
        if(casoNuevo == null){
            return ResponseEntity.status(400).body("El caso no puede estar vacio");
        }
        Caso casoExistente = casoService.update(id, casoNuevo);
        return casoExistente != null ? ResponseEntity.ok(casoExistente) : ResponseEntity.status(404).body("Caso no encontrado con ID: " + id);
    }
}
