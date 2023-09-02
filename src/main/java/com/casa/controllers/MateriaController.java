package com.casa.controllers;

import java.util.Map;

import com.casa.feignclient.dtos.MateriaRegistrarDto;
import com.casa.services.MateriaService;
import com.casa.utils.RouteFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casa.domain.dtos.ResponseMainDto;
import com.casa.utils.Constantes;
import com.casa.utils.Route;

@RestController
@RequestMapping(value = Route.COLEGIOS + RouteFeign.MATERIA, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class MateriaController {

	@SuppressWarnings("unused")
	@Autowired
	private MateriaService materiaSvc;

	@PostMapping(value = Route.REGISTRAR)
	public ResponseEntity<ResponseMainDto> registrar(@RequestBody MateriaRegistrarDto materia) {
		Map<String, Object> map = materiaSvc.registrar(materia);
		String error = (String)map.get("error");
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		if(error != null) {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_CONSULTA_FALLIDA,
					error), HttpStatus.OK);
		}
		if(errorNoExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_CONSULTA_FALLIDA,
					errorNoExiste), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_CONSULTA_EXITOSA,
					map.get(Constantes.MAP_RESPONSE)), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = RouteFeign.CONSULTARPOR_NOMBRE)
	public ResponseEntity<ResponseMainDto> consultarPorNombre(@PathVariable String nombre) {
		Map<String, Object> map = materiaSvc.consultarPorNombre(nombre);
		String errorNoExiste = (String)map.get(Constantes.MAP_ERROR_NOEXISTENCIA);
		if(errorNoExiste != null) {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_CONSULTA_FALLIDA, 
					errorNoExiste), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_CONSULTA_EXITOSA, 
					map.get(Constantes.MAP_RESPONSE)), HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = Route.TODAS)
	public ResponseEntity<ResponseMainDto> consultarTodas() {
		Map<String, Object> map = materiaSvc.consultarTodas();
		String error = (String)map.get("error");
		if(error != null) {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_CONSULTA_FALLIDA, 
					error), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMainDto(Constantes.TTL_CONSULTA_EXITOSA, 
					map.get(Constantes.MAP_RESPONSE)), HttpStatus.OK);
		}
	}

}
