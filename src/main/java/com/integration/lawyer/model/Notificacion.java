package com.integration.lawyer.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String mensaje;
    
    @Column(nullable = false, length = 100)
    private String update_at;

    @PrePersist
    @PreUpdate
    private void setUpdateAt() {
        this.update_at = LocalDateTime.now().toString();
    }

    //opcional dado a que se puede crear desde agenda o comentario
    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private Comentario comentario;

}
