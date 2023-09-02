package com.casa.controllers;

import java.util.Map;

import com.casa.services.UsuarioService;
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

import com.casa.domain.dtos.ResponseMainDto;
import com.casa.domain.dtos.UsuarioModificarDto;
import com.casa.domain.dtos.UsuarioRegistrarDto;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.COLEGIOS+Route.USUARIO, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@SuppressWarnings("unused")
	@Autowired
	private UsuarioService usuarioSvc;
	
	@PutMapping(value = Route.MODIFICAR)
	public ResponseEntity<ResponseMainDto> modificarTodo(@RequestBody UsuarioModificarDto usuario) {
		Map<String, Object> map = usuarioSvc.modificarTodo(usuario);
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
	
	@DeleteMapping(value = Route.ELIMINACION_LOGICA)
	public ResponseEntity<ResponseMainDto> eliminacionLogica(@PathVariable String id) {
		Map<String, Object> map = usuarioSvc.eliminacionLogica(Long.parseLong(id));
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		if(errorNoExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_FALLIDA, errorNoExiste), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_EXITOSA, map.get(Constantes.MAP_RESPONSE)), HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = Route.ELIMINAR)
	public ResponseEntity<ResponseMainDto> eliminar(@PathVariable Long id) {
		Map<String, Object> map = usuarioSvc.eliminar(id);
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		if(errorNoExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_FALLIDA, errorNoExiste), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_ELIMINACION_EXITOSA, map.get(Constantes.MAP_RESPONSE)), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = Route.TODOS_EXISTENTES)
	public ResponseEntity<ResponseMainDto> consultarExistentes() {
		return new ResponseEntity<>(new ResponseMainDto
				(Constantes.TTL_CONSULTA_EXITOSA, usuarioSvc.consultarExistentes()), HttpStatus.OK);
	}
	
	@GetMapping(value = Route.TODOS)
	public ResponseEntity<ResponseMainDto> consultarTodos() {
		return new ResponseEntity<>(new ResponseMainDto
				(Constantes.TTL_CONSULTA_EXITOSA, usuarioSvc.consultarTodos()), HttpStatus.OK);
	}
	
	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<ResponseMainDto> registrar(@RequestBody UsuarioRegistrarDto usuario) {
		Map<String, Object> map = usuarioSvc.registrar(usuario);
		String errorCamposVacios = (String)map.get(Constantes.MAP_ERROR_CAMPOS_VACIOS);
		String errorNoPrefijo = (String)map.get(Constantes.MAP_ERROR_NOPREFIJO);
		String errorSiExiste = (String)map.get(Constantes.MAP_ERROR_SIEXISTENCIA);
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		if(errorCamposVacios != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.NOT_FOUND);
		}
		if(errorNoPrefijo != null) {
			return new ResponseEntity<>(new ResponseMainDto
					(Constantes.TTL_REGISTRO_FALLIDO, errorNoPrefijo), HttpStatus.BAD_REQUEST);
		}
		if(errorSiExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_REGISTRO_FALLIDO, 
					errorSiExiste), HttpStatus.BAD_REQUEST);
		}
		if(errorNoExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_REGISTRO_FALLIDO,
					errorNoExiste), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_REGISTRO_EXITOSO, 
					map.get(Constantes.MAP_RESPONSE)), HttpStatus.CREATED);
		}
	}

}
