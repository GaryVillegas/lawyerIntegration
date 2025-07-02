package com.integration.lawyer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.lawyer.model.Notificacion;
import com.integration.lawyer.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public Notificacion crear(Notificacion notification){
        return notificacionRepository.save(notification);
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public Notificacion findById(Integer id){
        return notificacionRepository.findById(id).orElse(null);
    }

    public void delete(Integer id){
        notificacionRepository.deleteById(id);
    }
}
