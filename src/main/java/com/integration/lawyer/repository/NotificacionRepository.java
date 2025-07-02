package com.integration.lawyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integration.lawyer.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer>{
}
