package com.integration.lawyer.model;

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

    // Si quieres relacionarlo con un usuario específico
    @Column(name = "usuario_id")
    private Integer usuarioId;
}