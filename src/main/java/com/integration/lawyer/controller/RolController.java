package com.integration.lawyer.controller;

import com.integration.lawyer.model.Rol;
import com.integration.lawyer.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping("/rol/{id}")
    public ResponseEntity<?> buscarRolId(@PathVariable Integer id){
        Rol rol = rolService.findById(id);
        if(rol == null){
            return ResponseEntity.status(404).body("Rol no encontrado con ID: " + id);
        }
        return ResponseEntity.status(201).body(rol);
    }

    @GetMapping("/rol")
    public ResponseEntity<?>listarRol(){
        if(rolService.findAll().isEmpty()){
            return ResponseEntity.status(400).body("No se encontraron roles: " + rolService.findAll());
        }
        return ResponseEntity.status(200).body(rolService.findAll());
    }

    @PostMapping("/rol/nuevo")
    public ResponseEntity<?> guardarRol(@RequestBody Rol rol){
        Rol rolGuardado = rolService.save(rol);
        return ResponseEntity.status(201).body(rolGuardado);
    }

    @DeleteMapping("/rol/delete/{id}")
    public ResponseEntity<?> borrarRol(@PathVariable Integer id) {
        rolService.delete(id);
        return ResponseEntity.status(200).body("Rol eliminado");
    }
    
    @PutMapping("/rol/edit/{id}")
    public ResponseEntity<String> actualizarRol(
    @PathVariable Integer id, 
    @RequestBody Rol nuevoRol) {
        if (nuevoRol.getNombreRol() == null || nuevoRol.getNombreRol().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del rol no puede estar vacío");
        }

        Rol actualizado = rolService.actualizar(id, nuevoRol);
        return actualizado != null 
            ? ResponseEntity.ok("Rol actualizado") 
            : ResponseEntity.notFound().build();
    }


}
