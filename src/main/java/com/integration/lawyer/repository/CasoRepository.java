package com.integration.lawyer.repository;

import com.integration.lawyer.model.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasoRepository extends JpaRepository<Caso, Integer> {
}
