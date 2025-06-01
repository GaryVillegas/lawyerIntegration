package com.integration.lawyer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "caso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false, length = 50, unique = true)
    private LocalDate fechaCierre;
}
