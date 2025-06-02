package com.integration.lawyer.Repository;

import com.integration.lawyer.Model.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasoRepository extends JpaRepository<Caso, Integer> {
}
