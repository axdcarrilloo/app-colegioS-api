package com.casa.services;

import java.util.HashMap;
import java.util.Map;

import com.casa.feignclient.dtos.MateriaRegistrarDto;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.feignclient.MateriaFeign;
import com.casa.utils.Constantes;

@Service
public class MateriaService {
	
	private final Logger log = LoggerFactory.getLogger(MateriaService.class);

	@SuppressWarnings("unused")
	@Autowired
	private MateriaFeign materiaFeign;

	public Map<String, Object> registrar(MateriaRegistrarDto materia) {
		Map<String, Object> map = new HashMap<>();
		try {
			log.info("MateriaService.class - registrar() -> Registrando materias desde Colegios...!");
			map.put(Constantes.MAP_RESPONSE, materiaFeign.registrar(materia).getRespuesta());
			return map;
		} catch (FeignException e) {
			if(e.status() == Constantes.HTTP_NOENCONTRADO) {
				map.put(Constantes.MAP_ERROR_NOEXISTENCIA, Constantes.MSG_NOEXISTENCIA);
			} else {
				map.put("error", "Error");
			}
			return map;
		}
	}

	public Map<String, Object> consultarPorNombre(String nombre) {
		log.info("MateriaService.class - consultarTodas() -> Consultando en horarios todas las Materias...!");
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(Constantes.MAP_RESPONSE, materiaFeign.consultarPorNombre(nombre).getRespuesta());
			return map;
		} catch (FeignException e) {
			if(e.status() == Constantes.HTTP_NOENCONTRADO) {
				map.put("errorNoExiste", "No se encuentra");
			} else {
				map.put("errorNoExiste", "Error");
			}
			return map;
		}
	}
	
	public Map<String, Object> consultarTodas() {
		log.info("MateriaService.class - consultarTodas() -> Consultando en horarios todas las Materias...!");
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(Constantes.MAP_RESPONSE, materiaFeign.consultarTodas().getRespuesta());
			return map;
		} catch (FeignException e) {
			map.put("error", "Error");
			return map;
		}
	}

}
