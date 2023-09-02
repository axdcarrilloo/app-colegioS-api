package com.casa.domain.dtos;

import java.time.LocalDateTime;

import com.casa.domain.entities.RolEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodigoRegistrarDto {

	private RolEntity rol;
	
	private String prefijo;
	
	private Integer consecutivo;
	
	private String descripcion;
	
	private Boolean eliminado;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;
	
}
