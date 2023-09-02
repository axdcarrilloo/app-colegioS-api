package com.casa.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModificarDto {
	
	private Long id;
	
	private String tipoUsuario;
	
	private String tipoDocumento;
	
	private String numeroDocumento;
	
	private String nombres;
	
	private String apellidos;
	
	private String celular;
	
	private String direccion;
	
	private String usuario;

}
