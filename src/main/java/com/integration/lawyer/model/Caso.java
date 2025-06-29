package com.integration.lawyer.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    // 🔗 Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;


    @OneToMany(mappedBy = "caso")
    @JsonManagedReference
    private List<Documento> documentos;

    @PrePersist
    @PreUpdate
    private void setFechaCreacion() {
        this.fechaCreacion = LocalDateTime.now().toString();
    }
}
