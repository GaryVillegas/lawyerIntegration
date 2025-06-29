package com.integration.lawyer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "area_asesoramiento")
@Data
@NoArgsConstructor
public class AreaAsesoramiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    // Relación inversa (opcional, para listar agendas desde el área)
    @OneToMany(mappedBy = "areaAsesoramiento")
    private List<Agenda> agendas;
}
