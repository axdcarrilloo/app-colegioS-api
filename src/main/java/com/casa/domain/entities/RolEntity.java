package com.casa.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RolEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String codigo;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(length = 2)
    private Boolean eliminado;

    @Column(nullable = false, name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(nullable = false, name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

}
