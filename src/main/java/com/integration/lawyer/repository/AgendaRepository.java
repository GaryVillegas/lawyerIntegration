package com.integration.lawyer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integration.lawyer.Model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
}
