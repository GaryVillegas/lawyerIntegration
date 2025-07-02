package com.integration.lawyer.repository;

import com.integration.lawyer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
    boolean existsByRolId(Integer rolId);
}
