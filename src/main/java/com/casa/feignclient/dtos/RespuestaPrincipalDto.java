package com.casa.feignclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaPrincipalDto {
	
	private String mensaje;
	
	private Object respuesta;
}
