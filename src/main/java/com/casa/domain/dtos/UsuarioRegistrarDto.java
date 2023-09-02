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
public class UsuarioRegistrarDto {
	
	private String codigo;
	
	private String tipoUsuario;
	
	private String tipoDocumento;
	
	private String numeroDocumento;
	
	private String nombres;
	
	private String apellidos;
	
	private String celular;
	
	private String direccion;
	
	private String usuario;
	
	private String contrasenna;
	
	private Boolean eliminado;
	
	private LocalDateTime fechaRegistro;

	private LocalDateTime fechaModificacion;

}
