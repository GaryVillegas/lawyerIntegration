package com.integration.lawyer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.lawyer.Model.Agenda;
import com.integration.lawyer.Service.AgendaService;

@RestController
@RequestMapping("/api/v1")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping("/agenda")
    public ResponseEntity<?> listarAgenda(){
        return ResponseEntity.status(200).body(agendaService.findAll());
    }

    @PostMapping("/agenda")
    public ResponseEntity<?> guardarAgenda(@RequestBody Agenda agenda){
        Agenda agendaGuardada = agendaService.save(agenda);
        return ResponseEntity.status(200).body(agendaGuardada);
    }

    @DeleteMapping("/agendaDelete/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        agendaService.delete(id);
        return ResponseEntity.status(201).body("Cita eliminada con éxito");
    }
}
