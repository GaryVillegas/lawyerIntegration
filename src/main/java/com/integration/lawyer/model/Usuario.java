package com.integration.lawyer.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String contrasena;

    @Column(nullable = false, length = 50, unique = true)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @JsonBackReference
    private Rol rol;


    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Caso> casos;


}

