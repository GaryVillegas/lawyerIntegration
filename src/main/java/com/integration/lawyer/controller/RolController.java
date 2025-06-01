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
//    @PutMapping("/rol/{id}")
//    public ResponseEntity<?> actualizarRol(@PathVariable Integer id, @RequestBody Rol nuevoRol) {
//        Rol actualizado = rolService.actualizar(id, nuevoRol);
//        if (actualizado != null) {
//            return ResponseEntity.status(200).body("Rol actualizado");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


}
