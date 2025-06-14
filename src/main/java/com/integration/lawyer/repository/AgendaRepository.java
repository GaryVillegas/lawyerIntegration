package com.integration.lawyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integration.lawyer.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
}
