package com.integration.lawyer.service;


import com.integration.lawyer.model.Rol;
import com.integration.lawyer.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll(){
        return rolRepository.findAll();
    }

    public Rol save(Rol rol){return rolRepository.save(rol);}

    public void delete(Integer id){
        rolRepository.deleteById(id);
    }
    
    public Rol actualizar(Integer id, Rol nuevoRol) {
        Rol rolExistente = rolRepository.findById(id).orElse(null);
        if (rolExistente == null) {
            return null;
        }
        
        rolExistente.setNombreRol(nuevoRol.getNombreRol());
        return rolRepository.save(rolExistente);
    }


}
