package com.casa.domain.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodigoExisteLogicoDto {
	
	private Long id;
	
	private String prefijo;
	
	private Integer consecutivo;
	
	private String descripcion;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
