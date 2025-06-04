package com.integration.lawyer.service;

import com.integration.lawyer.model.Caso;
import com.integration.lawyer.repository.CasoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CasoService {
    @Autowired
    private CasoRepository casoRepository;

    public List<Caso> findAll() {
        return casoRepository.findAll();}

    public Caso findById(Integer id){
        return casoRepository.findById(id).orElse(null);//retornar completo
    }
    public void delete(Integer id){
        casoRepository.deleteById(id);
    }

    //crear caso
    public Caso save(Caso caso){
        return  casoRepository.save(caso);
    }

    //modificar o actualizar caso
    public Caso update(Integer id ,Caso casoNuevo){
        Caso casoExistente = casoRepository.findById(id).orElse(null);
        if(casoExistente == null) {
            return null; // Caso no encontrado
        }

        casoExistente.setDescripcion(casoNuevo.getDescripcion());
        casoExistente.setEstado(casoNuevo.getEstado());
        casoExistente.setTitulo(casoNuevo.getTitulo());
        casoExistente.setUsuarioId(casoNuevo.getUsuarioId());
        casoExistente.setFechaCreacion(casoNuevo.getFechaCreacion());
        casoExistente.setFechaCierre(casoNuevo.getFechaCierre());
        return casoRepository.save(casoExistente);
    }
}
