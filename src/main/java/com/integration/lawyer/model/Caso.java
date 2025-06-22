package com.integration.lawyer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String fechaCreacion;

    @Column(nullable = false, length = 50)
    private String fechaCierre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    //Se va a tener que agregar un columna con foreign key hacia usuarios que tengan de rol
    //cliente para que les pueda llegar notificaciones sobre el caso.

    @PrePersist
    @PreUpdate
    private void setFechaCreacion() {
        this.fechaCreacion = LocalDateTime.now().toString();
    }
}
