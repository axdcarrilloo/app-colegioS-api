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
public class MateriaRegistrarDto {

    private String nombre;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaModificacion;

}
