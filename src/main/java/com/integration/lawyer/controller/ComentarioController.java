package com.integration.lawyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.lawyer.model.Comentario;
import com.integration.lawyer.service.ComentarioService;

@RestController
@RequestMapping("/api/v1")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    //Crear un comentario
    @PostMapping("/comentario/nuevo")
    public ResponseEntity<?> crearComentario(@RequestBody Comentario comentario){
        Comentario comentarioGuardado = comentarioService.Save(comentario);
        if (comentarioGuardado == null) {
            return ResponseEntity.status(400).body("Error al guardar el comentario");
        } else if(comentarioGuardado.getComentario() == null || comentarioGuardado.getComentario().isEmpty()){
            return ResponseEntity.status(400).body("El comentario no puede estar vacío");
        }
        return ResponseEntity.status(201).body("comentario guardado con exito: " + comentarioGuardado.getId());
    }

    //Buscar comentario por id
    @GetMapping("/comentario/{id}")
    public ResponseEntity<?> buscarComentarioById(@PathVariable Integer id){
        Comentario comentario = comentarioService.findById(id);
        if(comentario == null){
            return ResponseEntity.status(404).body("No hay comentarios con ese id");
        }
        return ResponseEntity.status(200).body("Comentario encontrado: " + comentario.getComentario());
    }

    //Listar todos los comentarios
    @GetMapping("/comentario")
    public ResponseEntity<?> listarComentarios(){
        if(comentarioService.findAll().isEmpty()){
            return ResponseEntity.status(404).body("No hay comentarios disponibles");
        }
        return ResponseEntity.status(200).body(comentarioService.findAll());
    }

}
