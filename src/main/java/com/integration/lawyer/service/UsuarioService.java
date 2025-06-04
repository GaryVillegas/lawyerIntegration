package com.integration.lawyer.service;

import com.integration.lawyer.model.Usuario;
import com.integration.lawyer.repository.UsuarioRepository;
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

    /**
     * Método para iniciar sesión de un usuario.
     * @param correo El correo electrónico del usuario.
     * @param contrasena La contraseña del usuario.
     * @return Un Optional que contiene el Usuario si las credenciales son correctas, o vacío si no lo son.
     */
    public Optional<Usuario> login(String correo,String contrasena){
        return usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    // Método para listar todos los usuarios.
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();}
    
    //Metodo para buscar un usuario segun su ID.
    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    //Metodo para eliminar usuario
    public void delete(Integer id){
        usuarioRepository.deleteById(id);
    }

    //metodo para guardar usuario
    public Usuario save(Usuario usuario){
        return  usuarioRepository.save(usuario);
    }

    //metodo para modificar usuario
    public Usuario actualizar(Integer id, Usuario usuarioNuevo){
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if(usuarioExistente == null){
            return null;
        }
        usuarioExistente.setCorreo(usuarioNuevo.getCorreo());
        usuarioExistente.setContrasena(usuarioNuevo.getContrasena());
        usuarioExistente.setRol(usuarioNuevo.getRol());
        return usuarioRepository.save(usuarioExistente);
    }

}
