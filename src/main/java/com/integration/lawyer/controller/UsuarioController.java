package com.integration.lawyer.controller;

import com.integration.lawyer.model.Usuario;
import com.integration.lawyer.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> listarUsuarios() {
        if(usuarioService.findAll().isEmpty()){
            return ResponseEntity.status(404).body("No se encontraron usuarios");
        }
        return ResponseEntity.status(200).body(usuarioService.findAll());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Integer id){
        Usuario usuario=usuarioService.findById(id);
        if (usuario == null){
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        return ResponseEntity.status(200).body(usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable Integer id){
        usuarioService.delete(id);
        return ResponseEntity.status(200).body("Usuario eliminado");
    }

    @PostMapping("/usuario/nuevo")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioGuardado = usuarioService.save(usuario);
        return ResponseEntity.status(201).body(usuarioGuardado);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario loginData) {
        Optional<Usuario> usuario = usuarioService.login(loginData.getCorreo(), loginData.getContrasena());
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.status(401).build());
    }

    //Metodo para actualizar un usuario
    //Validar que los campos correo, contraseña y rol no sean nulos
    @PutMapping("/usuario/editar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioNuevo){
        if(usuarioNuevo.getCorreo() == null || usuarioNuevo.getContrasena() == null || usuarioNuevo.getRol() == null){
            return ResponseEntity.status(404).body("Los campos correo, contraseña y rol son obligatorios");
        }

        Usuario usuarioExistente = usuarioService.actualizar(id, usuarioNuevo);
        return usuarioExistente != null ? ResponseEntity.ok(usuarioExistente) : ResponseEntity.notFound().build();
    }
}
