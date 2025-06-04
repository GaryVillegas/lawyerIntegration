package com.integration.lawyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integration.lawyer.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
}
