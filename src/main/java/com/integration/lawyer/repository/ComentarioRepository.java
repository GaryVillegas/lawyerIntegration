package com.integration.lawyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integration.lawyer.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
