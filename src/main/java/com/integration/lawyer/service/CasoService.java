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
    public Caso save(Caso caso){
        return  casoRepository.save(caso);
    }
}
