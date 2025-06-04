package com.integration.lawyer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.lawyer.model.Agenda;
import com.integration.lawyer.repository.AgendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;
    
    //Ver todas las citas agendadas
    public List<Agenda> findAll(){
        return agendaRepository.findAll();
    }

    //Encontrar cita por ID
    public Agenda findById(Integer id){
        return agendaRepository.findById(id).orElse(null);
    }

    //Guardar una nueva cita
    public Agenda save(Agenda agenda){
        return agendaRepository.save(agenda);
    }

    //Borrar una cita por ID
    public void delete(Integer id){
        agendaRepository.deleteById(id);
    }

    //modificar una cita por ID
    public Agenda actualizar(Integer id, Agenda agendaNueva){
        Agenda agendaExistente = agendaRepository.findById(id).orElse(null);
        if(agendaExistente == null){
            return null;
        }

        agendaExistente.setAbogadoSeleccionado(agendaNueva.getAbogadoSeleccionado());
        agendaExistente.setIdUsuario(agendaNueva.getIdUsuario());
        agendaExistente.setFechaReunion(agendaNueva.getFechaReunion());
        agendaExistente.setHoraReunion(agendaNueva.getHoraReunion());
        agendaExistente.setAreaAsesoramiento(agendaNueva.getAreaAsesoramiento());
        return agendaRepository.save(agendaExistente);
    }
}
