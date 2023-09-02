package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casa.domain.entities.NotaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.NotaRegistrarDto;
import com.casa.domain.mappers.NotaMapper;
import com.casa.repositories.NotaRepository;
import com.casa.utils.Constantes;

@Service
public class NotaService {
	
	private final Logger log = LoggerFactory.getLogger(NotaService.class);
	
	@Autowired
	private NotaRepository notaRepository;
		
	@Autowired
	private UsuarioService usuarioSvc;
	
	private Boolean validarCamposObligarotiosRegistro(NotaRegistrarDto nota) {
		log.info("NotaService.class : validarCamposObligarotios() -> Validando campos obligatorios...!");
		if(nota.getNota() == null) {
			return true;
		} 
		if(nota.getEstudiante() == null) {
			return true;
		}
		if(usuarioSvc.consultarPorId(nota.getEstudiante().getId()) == null) {
			return true;
		}
		if(nota.getProfesor() == null) {
			return true;
		}
		return usuarioSvc.consultarPorId(nota.getProfesor().getId()) == null;
	}
	
	public List<NotaEntity> consultarTodas() {
		log.info("NotaService.class : consultarTodas() -> Consultando todas las Notas...!");
		return notaRepository.findAll();	
	}
	
	public Map<String, Object> registrar(NotaRegistrarDto nota) {
		log.info("NotaService.class : registrar() -> Registrando Nota...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposObligarotiosRegistro(nota))) {
			map.put(Constantes.MAP_ERROR_CAMPOS_VACIOS, Constantes.MSG_CAMPOS_VACIOS);
			return map;
		} else {
			nota.setFechaRegistro(Constantes.obtenerFechaActual());
			nota.setFechaModificacion(Constantes.obtenerFechaActual());
			map.put(Constantes.MAP_RESPONSE, notaRepository.save(NotaMapper.convertirDtoToEntity(nota)).getId());
			return map;
		}
	}

}
