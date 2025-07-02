package com.integration.lawyer.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.integration.lawyer.model.Notificacion;

import jakarta.transaction.Transactional;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Notificacion n WHERE n.agenda.id = :agendaId")
    void deleteByAgendaId(Integer agendaId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Notificacion n WHERE n.comentario.id = :comentarioId")
    void deleteByComentarioId(Integer comentarioId);
}
