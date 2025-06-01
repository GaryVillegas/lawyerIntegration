package com.integration.lawyer.model;

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


    @Column(nullable = false, length = 50, unique = true)
    private String rol;

    public String getCorreo() {
        return this.correo = correo;
    }

    public String getContrasena() {
        return this.contrasena = contrasena;
    }
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
