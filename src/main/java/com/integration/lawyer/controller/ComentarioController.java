package com.integration.lawyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.integration.lawyer.model.Comentario;
import com.integration.lawyer.model.Notificacion;
import com.integration.lawyer.model.Usuario;
import com.integration.lawyer.service.ComentarioService;
import com.integration.lawyer.service.NotificacionService;
import com.integration.lawyer.service.UsuarioService;

@RestController
@RequestMapping("/api/v1")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotificacionService notificacionService;

    //Crear un comentario
    @PostMapping("/comentario/nuevo")
    public ResponseEntity<?> crearComentario(@RequestBody Comentario comentario){
        //validar que el comentario no sea nulo
        if(comentario.getComentario() == null){
            return ResponseEntity.status(400).body("El comentario no puede estar vacio");
        }
        //validar que el usuario exista
        Usuario usuario = usuarioService.findById(comentario.getIdUsuario()); //importamos el servicio para validar datos del usuario
        if(usuario == null){
            return ResponseEntity.status(404).body("Usuario no encontrado: " + comentario.getNombre());
        }

        try{
            Comentario comentarioGuardado = comentarioService.Save(comentario);

            Notificacion notificacion = new Notificacion();
            notificacion.setMensaje("Nuevo comentario agregado por " + usuario.getNombre());
            notificacion.setComentario(comentarioGuardado);

            notificacionService.crear(notificacion);

            return ResponseEntity.status(201).body(comentarioGuardado);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error al guardar el comentario: " + e.getMessage());
        }
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

    //borrar comentario por id
    @DeleteMapping("/comentario/delete/{id}")
    public ResponseEntity<?> borrarComentario(@PathVariable Integer id){
        if(comentarioService.findById(id) == null){
            return ResponseEntity.status(404).body("Comentario no encontrado con el id: " + id);
        }
        notificacionService.eliminarPorComentarioId(id);
        comentarioService.delete(id);
        return ResponseEntity.status(201).body("Comentario eliminado con exito.");
    }

    //modificar comentario por ud
    @PutMapping("/comentario/editar/{id}")
    public ResponseEntity<?> actualizarComentario(
        @PathVariable Integer id,
        @RequestBody Comentario comentarioNuevo){
            if(comentarioNuevo.getComentario() == null || comentarioNuevo.getComentario().trim().isEmpty()){
                return ResponseEntity.badRequest().body("El comentario no puede estar vacio");
            }

            Comentario comentarioExistente = comentarioService.actualizar(id, comentarioNuevo);
            return comentarioExistente != null ? ResponseEntity.ok(comentarioExistente) : ResponseEntity.notFound().build();
        }

}
