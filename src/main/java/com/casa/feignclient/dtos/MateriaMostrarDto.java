package com.casa.feignclient.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaMostrarDto {
	
	private Long id;
	
	private String nombre;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime fechaModificacion;

}
