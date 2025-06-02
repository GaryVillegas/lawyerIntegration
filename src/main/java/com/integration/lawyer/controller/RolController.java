package com.integration.lawyer.Controller;

import com.integration.lawyer.Model.Rol;
import com.integration.lawyer.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping("/rol")
    public ResponseEntity<?> guardarRol(@RequestBody Rol rol){
        Rol rolGuardado = rolService.save(rol);
        return ResponseEntity.status(201).body(rolGuardado);
    }

    @GetMapping("/rol")
    public ResponseEntity<?>listarRol(){
        return ResponseEntity.status(200).body(rolService.findAll());
    }

    @DeleteMapping("/rol/{id}")
    public ResponseEntity<?> borrarRol(@PathVariable Integer id) {
        rolService.delete(id);
        return ResponseEntity.status(200).body("Rol eliminado");
    }
    
    @PutMapping("/rol/{id}")
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
