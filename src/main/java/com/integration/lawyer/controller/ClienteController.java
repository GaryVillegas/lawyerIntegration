package com.integration.lawyer.Controller;

import com.integration.lawyer.Model.Cliente;
import com.integration.lawyer.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> listarCliente(@PathVariable Integer id){
        Cliente cliente=clienteService.findById(id);
        if (cliente == null){
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        return ResponseEntity.status(200).body(cliente);
    }
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> borrarClientes(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.status(200).body("Cliente eliminado");
    }
    @PostMapping("/cliente")
    public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente){
        Cliente clienteGuardado = clienteService.save(cliente);
        return ResponseEntity.status(201).body(clienteGuardado);
    }
}
