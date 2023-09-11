package com.casa.controllers;

import java.util.Map;

import com.casa.utils.MensajesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.NotaRegistrarDto;
import com.casa.domain.dtos.ResponseMainDto;
import com.casa.services.NotaService;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.COLEGIOS + Route.NOTA, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class NotaController {
	
	@SuppressWarnings("unused")
	@Autowired
	private NotaService notaSvc;
	
	@GetMapping(value = Route.TODAS)
	public ResponseEntity<ResponseMainDto> consultarTodas() {
		return new ResponseEntity<>(new ResponseMainDto
				(MensajesProperties.TTL_CONSULTA_EXITOSA, notaSvc.consultarTodas()), HttpStatus.OK);
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<ResponseMainDto> registrar(@RequestBody NotaRegistrarDto nota) {
		Map<String, Object> map = notaSvc.registrar(nota);
		String errorCamposVacios = (String)map.get(Constantes.MAP_ERROR_CAMPOS_VACIOS);
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(MensajesProperties.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new ResponseMainDto
					(MensajesProperties.TTL_REGISTRO_EXITOSO, map.get(Constantes.MAP_RESPONSE)), HttpStatus.BAD_REQUEST);
		}
		
	}

}
