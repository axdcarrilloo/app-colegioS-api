package com.casa.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodigoModificarDto {
	
//	Cuando se valla a modificar un codigo 1ro se debe verificar si este ya tiene registros con su prefijo
	
	private Long id;
	
	private String prefijo;
	
	private Integer consecutivo;
	
	private String descripcion;

}
