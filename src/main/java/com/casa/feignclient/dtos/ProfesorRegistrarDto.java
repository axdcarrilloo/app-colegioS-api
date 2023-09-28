package com.casa.feignclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProfesorRegistrarDto {

    private String codigo;

    private String tipoUsuario;

    private String tipoDocumento;

    private String numeroDocumento;

    private String nombres;

    private String apellidos;

    private String celular;

    private String direccion;

    private Integer edad;

    private String email;

    private String usuario;

    private String contrasenna;

    private Boolean eliminado;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaModificacion;

}
