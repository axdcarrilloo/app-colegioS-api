package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.casa.domain.entities.CodigoEntity;
import com.casa.domain.entities.RolEntity;
import com.casa.utils.MensajesProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.CodigoExisteLogicoDto;
import com.casa.domain.dtos.CodigoModificarDto;
import com.casa.domain.dtos.CodigoRegistrarDto;
import com.casa.domain.mappers.CodigoMapper;
import com.casa.repositories.CodigoRepository;
import com.casa.utils.Constantes;

@Service
public class CodigoService {
	
	private final Logger log = LoggerFactory.getLogger(CodigoService.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private CodigoRepository codigoRepository;

	@SuppressWarnings("unused")
	@Autowired
	private RolService rolSvc;
	
	private Boolean validarCamposObligatoriosModificacion(CodigoModificarDto codigo) {
		log.info("CodigoService.class : validarCamposObligatoriosRegistrar() -> Validando campos obligatorios...!");
		if(codigo.getPrefijo() == null || codigo.getPrefijo().isEmpty()) {
			return true;
		}
		return codigo.getConsecutivo() == null;
	}
	
	private Boolean validarCamposObligatoriosRegistro(CodigoRegistrarDto codigoDto) {
		log.info("CodigoService.class : validarCamposObligatoriosRegistrar() -> Validando campos obligatorios...!");
		return codigoDto.getPrefijo() == null || codigoDto.getPrefijo().isEmpty();
	}

	public Boolean existenciaPorId(Long id) {
		log.info("CodigoService.class : existenciaPorId() -> Validando existencia por Id...!");
		return consultarPorId(id) != null;
	}
	
	public Map<String, Object> eliminacionLogica(Long id) {
		log.info("CodigoService.class : eliminacionLogica() -> Eliminando logicamente...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(existenciaPorId(id))) {
			codigoRepository.eliminacionLogica(id, true);
			map.put(Constantes.MAP_RESPONSE, id);
		} else {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
		}
		return map;
	}
	
	public Map<String, Object> eliminar(Long id) {
		log.info("CodigoService.class : eliminar() -> Eliminando realmente...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(existenciaPorId(id))) {
			codigoRepository.deleteById(id);
			map.put(Constantes.MAP_RESPONSE, id);
		} else {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
		}
		return map;
	}

	public Boolean existenciaPorRol(RolEntity rol) {
		log.info("CodigoService.class : existenciaPorId() -> Validando existencia por Rol...!");
		return consultarPorRol(rol) != null;
	}

	public CodigoEntity consultarPorRol(RolEntity rol) {
		log.info("CodigoService.class : consultarPorRol() -> Consultando por Rol...!");
		return codigoRepository.findByRol(rol);
	}
	
	public String asignarCodigo(String codigoRol) {
		log.info("CodigoService.class : asignarCodigo() -> Asignando codigo del prefijo...!");
		RolEntity rol = rolSvc.consultarPorCodigo(codigoRol);
		if(rol == null) {
			return null;
		}
		CodigoEntity codigoEntity = consultarPorRol(rol);
		if(codigoEntity == null) {
			return null;
		}
		String codigo = "";
		CodigoEntity codigoResponse = consultarPorPrefijo(codigoEntity.getPrefijo());
		String consecutivoString = String.valueOf(codigoResponse.getConsecutivo());
		int consecutivoCaracteres = consecutivoString.length();
		switch (consecutivoCaracteres) {
			case 1:
				codigo = codigoResponse.getPrefijo() + "00" + consecutivoString;
				break;
			case 2:
				codigo = codigoResponse.getPrefijo() + "0" + consecutivoString;
				break;
			case 3:
				codigo = codigoResponse.getPrefijo() + consecutivoString;
				break;
			default:
				break;
		}
		modificarTodo(new CodigoModificarDto(codigoResponse.getId(), codigoResponse.getPrefijo(), (codigoResponse.getConsecutivo() + 1),
				codigoResponse.getDescripcion()));
		return codigo;
	}
	
	public CodigoEntity consultarPorId(Long id) {
		log.info("CodigoService.class : consultarPorId() -> Consultando codigo por id...!");
		Optional<CodigoEntity> codigoOpt = codigoRepository.findById(id);
		return codigoOpt.orElse(null);
	}

	public Boolean existenciaPorPrefijo(String prefijo) {
		log.info("CodigoService.class : existenciaPorPrefijo() -> Consultando existencia por prefijo...!");
		return consultarPorPrefijo(prefijo)  != null;
	}
	
	public CodigoEntity consultarPorPrefijo(String prefijo) {
		log.info("CodigoService.class : consultarCodigoPorPrefijo() -> Consultando codigo por prefijo...!");
		Optional<CodigoEntity> optional = codigoRepository.findByPrefijo(prefijo);
		return optional.orElse(null);
	}
	
	public List<CodigoExisteLogicoDto> consultarExistentes() {
		log.info("CodigoService.class : consultarExistentes() -> Consultando los codigos existentes logicamente...!");
		return CodigoMapper.convertirDeListEntityToListDto(codigoRepository.findByEliminado(false));
	}

	public List<CodigoEntity> consultarTodos() {
		log.info("CodigoService.class : consultarTodos() -> Consultando todos los codigos...!");
		return codigoRepository.findAll();
	}

	public Map<String, Object> registrar(CodigoRegistrarDto codigoDto) {
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposObligatoriosRegistro(codigoDto))) {
			map.put(Constantes.MAP_ERROR_CAMPOS_VACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
			return map;
		}
		if(existenciaPorPrefijo(codigoDto.getPrefijo())) {
			map.put(Constantes.MAP_ERROR_SIEXISTENCIA, MensajesProperties.MSG_SIEXISTENCIA);
			return map;
		}
		RolEntity rol = rolSvc.consultarPorId(codigoDto.getRol().getId());
		if(rol == null) {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
			return map;
		}
		if(existenciaPorRol(rol)) {
			map.put(Constantes.MAP_ERROR_SIEXISTENCIA, MensajesProperties.MSG_SIEXISTENCIA);
		}else {
			log.info("CodigoService.class : registrar() -> Registrando codigo...!");
			codigoDto.setRol(rol);
			codigoDto.setConsecutivo(1);
			codigoDto.setEliminado(false);
			codigoDto.setFechaRegistro(Constantes.obtenerFechaActual());
			codigoDto.setFechaModificacion(Constantes.obtenerFechaActual());
			map.put(Constantes.MAP_RESPONSE, codigoRepository.save(CodigoMapper.convertirDeDtoToEntity(codigoDto)).getId());
		}
		return map;
	}

	public Map<String, Object> modificarTodo(CodigoModificarDto codigo) {
		log.info("CodigoService.class : modificarCodigo() -> Modificando codigo...!");
		Map<String, Object> map = new HashMap<>();
		CodigoEntity codigoMain = consultarPorId(codigo.getId());
		if(Boolean.TRUE.equals(validarCamposObligatoriosModificacion(codigo))) {
			map.put(Constantes.MAP_ERROR_CAMPOS_VACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
			return map;
		}
		if(codigoMain == null) {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
		} else {
			codigoRepository.modificar(codigo.getId(), codigo.getPrefijo(), codigo.getConsecutivo(), codigo.getDescripcion(), 
					Constantes.obtenerFechaActual());
			map.put(Constantes.MAP_RESPONSE, codigoMain.getId());
		}
		return map;
	}

}
