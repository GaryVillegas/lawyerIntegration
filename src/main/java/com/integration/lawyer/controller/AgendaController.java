package com.integration.lawyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.integration.lawyer.model.Agenda;
import com.integration.lawyer.model.Notificacion;
import com.integration.lawyer.model.Usuario;
import com.integration.lawyer.service.AgendaService;
import com.integration.lawyer.service.NotificacionService;
import com.integration.lawyer.service.UsuarioService;

@RestController
@RequestMapping("/api/v1")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/agenda")
    public ResponseEntity<?> listarAgenda(){
        if(agendaService.findAll().isEmpty()){
            return ResponseEntity.status(404).body("No hay citas agendadas");
        }
        return ResponseEntity.status(200).body(agendaService.findAll());
    }

    //Buscar una cita por ID
    @GetMapping("/agenda/{id}")
    public ResponseEntity<?> buscarAgendaById(@PathVariable Integer id){
        Agenda agenda = agendaService.findById(id);
        if(agenda == null){
            return ResponseEntity.status(404).body("No hay citas agendadas con ese ID");
        }
        return ResponseEntity.status(201).body(agenda);
    }

    //Crear una nueva cita
    @PostMapping("/agenda/nuevo")
    public ResponseEntity<?> guardarAgenda(@RequestBody Agenda agenda){
        if(agenda == null){
            return ResponseEntity.status(400).body("La cita no puede estar vacía");
        }
        //validar que el usuario exista
        Usuario usuario = usuarioService.findById(agenda.getIdUsuario());
        if(usuario == null){
            return ResponseEntity.status(404).body("Usuario no encontrado con ID: " + agenda.getIdUsuario());
        }
        //Validar que el usuario tenga permisos para agendar citas
        // Asumiendo que el rol del usuario es un campo en la entidad Usuario
        if(usuario.getRol().getNombreRol().equals("cliente")){
            return ResponseEntity.status(403).body("El usuario no tiene permisos para agendar citas");
        }

        Usuario abogado = usuarioService.findById(agenda.getIdUsuario());
        String nombreAbogado = (abogado != null) ? abogado.getNombre() : "Desconocido";

        Agenda agendaGuardada = agendaService.save(agenda);
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Tienes una nueva cita @" + nombreAbogado);
        notificacion.setAgenda(agendaGuardada);
        notificacionService.crear(notificacion);
        return ResponseEntity.status(200).body(agendaGuardada);
    }

    @DeleteMapping("/agendaDelete/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        notificacionService.eliminarPorAgendaId(id);
        agendaService.delete(id);
        return ResponseEntity.status(201).body("Cita eliminada con éxito");
    }

    //Editar una cita por ID
    @PutMapping("/agenda/editar/{id}")
    public ResponseEntity<?> editarAgenda(@PathVariable Integer id, @RequestBody Agenda agendaNueva){
        if(agendaNueva == null){
            return ResponseEntity.badRequest().body("La agenda no puede estar vacia");
        }

        Agenda agendaExistente = agendaService.actualizar(id, agendaNueva);
        return agendaExistente != null ? ResponseEntity.ok(agendaExistente) : ResponseEntity.notFound().build();
    }
}
