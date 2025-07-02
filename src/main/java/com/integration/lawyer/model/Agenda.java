package com.integration.lawyer.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agenda")
@Data
@NoArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nueva relación con AreaAsesoramiento
    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    @JsonBackReference
    private AreaAsesoramiento areaAsesoramiento;

    @Column(nullable = false, length = 50)
    private String fechaReunion;

    @Column(nullable = false, length = 50)
    private String abogadoSeleccionado;

    @Column(nullable = false)
    private Integer idUsuario;

    @Column(nullable = false, length = 50)
    private String horaReunion;
}
