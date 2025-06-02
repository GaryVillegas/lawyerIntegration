package com.integration.lawyer.Service;

import com.integration.lawyer.Model.Usuario;
import com.integration.lawyer.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public Optional<Usuario> login(String correo,String contrasena){
        return usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();}
    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public void delete(Integer id){
        usuarioRepository.deleteById(id);
    }
    public Usuario save(Usuario usuario){
        return  usuarioRepository.save(usuario);
    }

}
