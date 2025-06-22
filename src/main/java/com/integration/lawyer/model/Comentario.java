package com.integration.lawyer.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer idUsuario;

    @Column(nullable = false, length = 500)
    private String comentario;

    @Column(nullable = false, length = 50)
    private String updateAt;

    //se debe de agregar foreign key para poder conectar los comentarios a un caso

    // Correct JPA callback method
    @PrePersist
    @PreUpdate
    private void setUpdateAt() {
        this.updateAt = LocalDateTime.now().toString();
    }

    
}
