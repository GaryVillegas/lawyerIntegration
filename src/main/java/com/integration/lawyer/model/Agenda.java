package com.integration.lawyer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="agenda")
@Data
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String areaAsesoramiento;

    @Column(nullable = false, length = 50)
    private String fechaReunion;

    @Column(nullable = false, length = 50)
    private String abogadoSeleccionado;

    @Column(nullable = false)
    private Integer idUsuario;

    @Column(nullable = false, length = 50)
    private String horaReunion;
}
