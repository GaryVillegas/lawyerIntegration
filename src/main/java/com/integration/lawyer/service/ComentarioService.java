package com.integration.lawyer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.lawyer.model.Comentario;
import com.integration.lawyer.repository.ComentarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    public ComentarioService(ComentarioRepository comentarioRepository){
        this.comentarioRepository = comentarioRepository;
    }

    //listar
    public List<Comentario> findAll(){
        return comentarioRepository.findAll();
    }

    //Buscar por id
    public Comentario findById(Integer id){
        return comentarioRepository.findById(id).orElse(null);
    }

    //Guardar comentario
    public Comentario Save(Comentario comentario){
        return comentarioRepository.save(comentario);
    }
}
