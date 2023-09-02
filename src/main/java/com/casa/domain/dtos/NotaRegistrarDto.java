package com.casa.domain.dtos;

import java.time.LocalDateTime;

import com.casa.domain.entities.UsuarioEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotaRegistrarDto {
	
	private Float nota;
	
    private UsuarioEntity estudiante;
	
    private UsuarioEntity profesor;
    
    private String mensaje;
	
	private Object respuesta;

	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
