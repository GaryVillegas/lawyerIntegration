package com.integration.lawyer.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 255)
    private String ruta;

    @Column(nullable = false)
    private Long tamaño;

    // Relación con Caso (muchos documentos pueden pertenecer a un caso)
    @ManyToOne
    @JoinColumn(name = "caso_id", nullable = false)
    @JsonBackReference
    private Caso caso;


    // Relación opcional con Usuario si lo necesitas
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
