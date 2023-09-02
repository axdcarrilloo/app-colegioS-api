package com.casa.controllers;

import java.util.Map;

import com.casa.services.CodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.domain.dtos.CodigoModificarDto;
import com.casa.domain.dtos.CodigoRegistrarDto;
import com.casa.domain.dtos.ResponseMainDto;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.COLEGIOS + Route.CODIGO, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CodigoController {
	
	@SuppressWarnings("unused")
	@Autowired
	private CodigoService codigoSvc;

	@PutMapping(value = Route.MODIFICAR)
	public ResponseEntity<ResponseMainDto> modificarTodo(@RequestBody CodigoModificarDto codigo) {
		Map<String, Object> map = codigoSvc.modificarTodo(codigo);
		String errorCamposVacios = (String)map.get(Constantes.MAP_ERROR_CAMPOS_VACIOS);
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_MODIFICACION_FALLIDA, errorCamposVacios), HttpStatus.BAD_REQUEST);
		}
		if(errorNoExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_MODIFICACION_FALLIDA, errorNoExiste), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_MODIFICACION_EXITOSA, map.get(Constantes.MAP_RESPONSE)), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = Route.TODOS_EXISTENTES)
	public ResponseEntity<ResponseMainDto> consularExistentes() {
		return new ResponseEntity<>(new ResponseMainDto
				(Constantes.TTL_CONSULTA_EXITOSA, codigoSvc.consultarExistentes()), HttpStatus.OK);
	}
	
	@GetMapping(value = Route.TODOS)
	public ResponseEntity<ResponseMainDto> consultarTodos() {
		return new ResponseEntity<>(new ResponseMainDto
				(Constantes.TTL_CONSULTA_EXITOSA, codigoSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@DeleteMapping(value = Route.ELIMINAR)
	public ResponseEntity<ResponseMainDto> eliminar(@PathVariable Long id){
		Map<String, Object> map = codigoSvc.eliminar(id);
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		ResponseEntity<ResponseMainDto> response;
		if(errorNoExiste != null) {
			response = new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_FALLIDA, errorNoExiste), HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_EXITOSA, map.get(Constantes.MAP_RESPONSE)), HttpStatus.OK);
		}
		return response;
	}
	
	@DeleteMapping(value = Route.ELIMINACION_LOGICA)
	public ResponseEntity<ResponseMainDto> eliminacionLogica(@PathVariable Long id){
		Map<String, Object> map = codigoSvc.eliminacionLogica(id);
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		ResponseEntity<ResponseMainDto> response;
		if(errorNoExiste != null) {
			response = new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_FALLIDA, errorNoExiste), HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_EXITOSA, map.get(Constantes.MAP_RESPONSE)), HttpStatus.OK);
		}
		return response;
	}
	
	@PostMapping(value = Route.REGISTRAR, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMainDto> registrar(@RequestBody CodigoRegistrarDto codigo) {
		Map<String, Object> map = codigoSvc.registrar(codigo);
		String errorCamposVacios = (String)map.get(Constantes.MAP_ERROR_CAMPOS_VACIOS);
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		String errorSiExiste = (String)map.get(Constantes.MAP_ERROR_SIEXISTENCIA);
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
		}
		if(errorNoExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_REGISTRO_FALLIDO, errorNoExiste), HttpStatus.BAD_REQUEST);
		}
		if(errorSiExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_REGISTRO_FALLIDO, errorSiExiste), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_REGISTRO_EXITOSO, map.get(Constantes.MAP_RESPONSE)), HttpStatus.CREATED);
		}
	}

}
