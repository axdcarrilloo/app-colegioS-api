package com.casa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.casa.utils.MensajesProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.UsuarioExisteLogicoDto;
import com.casa.domain.dtos.UsuarioModificarDto;
import com.casa.domain.dtos.UsuarioRegistrarDto;
import com.casa.domain.entities.UsuarioEntity;
import com.casa.domain.mappers.UsuarioMapper;
import com.casa.repositories.UsuarioRepository;
import com.casa.utils.Constantes;

@Service
public class UsuarioService {
	
	private final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@SuppressWarnings("unused")
	@Autowired
	private UsuarioRepository usuarioRepository;

	@SuppressWarnings("unused")
	@Autowired
	private CodigoService codigoSvc;

	private Boolean validarCamposVaciosModificacion(UsuarioModificarDto usuario) {
		log.info("UsuarioService.class : validarCamposVacios() -> Validando campos de registro...!");
		if(usuario.getId() == null) {
			return true;
		}
		if(usuario.getTipoUsuario() == null) {
			return true;
		}
		if(usuario.getTipoDocumento() == null) {
			return true;
		}
		if(usuario.getNumeroDocumento() == null) {
			return true;
		}
		if(usuario.getApellidos() == null) {
			return true;
		}
		if(usuario.getCelular() == null) {
			return true;
		}
		return usuario.getUsuario() == null;
	}
	
	private Boolean validarCamposVaciosRegistro(UsuarioRegistrarDto usuario) {
		log.info("UsuarioService.class : validarCamposVacios() -> Validando campos de registro...!");
		if(usuario.getTipoUsuario() == null) {
			return true;
		}
		if(usuario.getTipoDocumento() == null) {
			return true;
		}
		if(usuario.getNumeroDocumento() == null) {
			return true;
		}
		if(usuario.getApellidos() == null) {
			return true;
		}
		if(usuario.getCelular() == null) {
			return true;
		}
		if(usuario.getUsuario() == null) {
			return true;
		}
		return usuario.getContrasenna() == null;
	}

	public Boolean existenciaPorNumeroDocumento(String numeroDocumento) {
		return consultarPorNumeroDocumento(numeroDocumento) != null;
	}
	
	public Map<String, Object> modificarTodo(UsuarioModificarDto usuario) {
		log.info("UsuarioService.class : modificarTodo() -> Modificando usuario...!");
		Map<String, Object> map = new HashMap<>();
		UsuarioEntity usuarioMain = consultarPorId(usuario.getId());
		if(Boolean.TRUE.equals(validarCamposVaciosModificacion(usuario))) {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
			return map;
		}
		if(usuarioMain == null) {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
		} else {
			usuarioRepository.modificarTodo(usuarioMain.getId(), usuario.getTipoUsuario(), usuario.getTipoDocumento(), 
					usuario.getNumeroDocumento(), usuario.getNombres(), usuario.getApellidos(), usuario.getCelular(), usuario.getDireccion(), usuario.getUsuario(), 
					Constantes.obtenerFechaActual());
			map.put(Constantes.MAP_RESPONSE, usuarioMain.getId());
		}
		return map;
	}
	
	public Boolean existenciaPorId(Long id) {
		return consultarPorId(id) != null;
	}
	
	public Map<String, Object> eliminacionLogica(Long id) {
		log.info("UsuarioService.class : eliminacionLogica() -> Eliminando usuario logicamente...!");		
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(existenciaPorId(id))) {
			usuarioRepository.eliminacionLogica(id, true);
			map.put(Constantes.MAP_RESPONSE, id);
		} else {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
		}
		return map;
	}
	
	public Map<String, Object> eliminar(Long id) {
		log.info("UsuarioService.class : eliminar() -> Eliminando usuario...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(existenciaPorId(id))) {
			usuarioRepository.deleteById(id);
			map.put(Constantes.MAP_RESPONSE, id);
		} else {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
		}
		return map;
	}
	
	public UsuarioEntity consultarPorNumeroDocumento(String numeroDocumento) {
		log.info("UsuarioService.class : consultarPorId() -> Consultando usuario por numero de documento...!");
		Optional<UsuarioEntity> optional = usuarioRepository.findByNumeroDocumento(numeroDocumento);
		return optional.orElse(null);
	}
	
	public UsuarioEntity consultarPorId(Long id) {
		log.info("UsuarioService.class : consultarPorId() -> Consultando usuario por Id...!");
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
		return optional.orElse(null);
	}
	
	public List<UsuarioExisteLogicoDto> consultarExistentes() {
		log.info("UsuarioService.class : consultarExistentes() -> Consultando usuarios existentes logicamente...!");
		return UsuarioMapper.convertirDeListEntityToListDto(usuarioRepository.findByEliminado(false));
	}
	
	public List<UsuarioEntity> consultarTodos(){
		log.info("UsuarioService.class : consultarTodos() -> Consultando todos los usuarios...!");
		return usuarioRepository.findAll();
	}

	public Map<String, Object> registrar(UsuarioRegistrarDto usuario) {
		log.info("UsuarioService.class : registrarUsuario() -> Registrando usuario...!");
		Map<String, Object> map = new HashMap<>();
		if(Boolean.TRUE.equals(validarCamposVaciosRegistro(usuario))) {
			map.put(Constantes.MAP_ERROR_CAMPOS_VACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
			return map;
		}
		if(Boolean.TRUE.equals(existenciaPorNumeroDocumento(usuario.getNumeroDocumento()))) {
			map.put(Constantes.MAP_ERROR_SIEXISTENCIA, MensajesProperties.MSG_SIEXISTENCIA);
			return map;
		}
		String codigo = codigoSvc.asignarCodigo(usuario.getTipoUsuario());
		if(codigo == null) {
			map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
		}else {
			usuario.setCodigo(codigo);
			usuario.setEliminado(false);
			usuario.setFechaRegistro(Constantes.obtenerFechaActual());
			usuario.setFechaModificacion(Constantes.obtenerFechaActual());
			map.put(Constantes.MAP_RESPONSE, usuarioRepository.save(UsuarioMapper.convertirDeDtoToEntity(usuario)).getId());
		}
		return map;
	}
}
