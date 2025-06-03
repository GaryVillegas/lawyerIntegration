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

    //Guardar una nueva cita
    public Agenda save(Agenda agenda){
        return agendaRepository.save(agenda);
    }

    //Borrar una cita por ID
    public void delete(Integer id){
        agendaRepository.deleteById(id);
    }
}
