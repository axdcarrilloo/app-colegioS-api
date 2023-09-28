package com.casa.domain.mappers;

import java.util.ArrayList;
import java.util.List;

import com.casa.domain.dtos.UsuarioExisteLogicoDto;
import com.casa.domain.dtos.UsuarioRegistrarDto;
import com.casa.domain.entities.UsuarioEntity;
import com.casa.feignclient.dtos.ProfesorRegistrarDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsuarioMapper {
	
	private static  Logger log = LoggerFactory.getLogger(UsuarioMapper.class);
	
	private UsuarioMapper() {}

	public static ProfesorRegistrarDto converirUsuarioDtoAProfesorDto(UsuarioRegistrarDto usuario) {
		return new ProfesorRegistrarDto(usuario.getCodigo(), usuario.getTipoUsuario(), usuario.getTipoDocumento(), usuario.getNumeroDocumento(), usuario.getNombres(),
				usuario.getApellidos(), usuario.getCelular(), usuario.getDireccion(), usuario.getEdad(), usuario.getEmail(), usuario.getUsuario(), usuario.getContrasenna(),
				usuario.getEliminado(), usuario.getFechaRegistro(), usuario.getFechaModificacion());
	}
	
	public static List<UsuarioExisteLogicoDto> convertirDeListEntityToListDto(List<UsuarioEntity> usuarios) {
		log.info("UsuarioMapper.class : convertirDeDtoToEntity() -> Convirtiendo UsuarioDto a Usuario Entity...!");
		List<UsuarioExisteLogicoDto> usuariosResponse = new ArrayList<>();
		usuarios.forEach(u -> {
			usuariosResponse.add(new UsuarioExisteLogicoDto(u.getId(), u.getCodigo(), u.getTipoUsuario(), u.getTipoDocumento(), u.getNumeroDocumento(),
					u.getNombres(), u.getApellidos(), u.getCelular(), u.getDireccion(), u.getUsuario(), u.getContrasenna(),
					u.getFechaRegistro(), u.getFechaModificacion()));
		});
		return usuariosResponse;
	}
	
	public static UsuarioEntity convertirDeDtoToEntity(UsuarioRegistrarDto usuario) {
		log.info("UsuarioMapper.class : convertirDeDtoToEntity() -> Convirtiendo UsuarioDto a Usuario Entity...!");
		return new UsuarioEntity(0L, usuario.getCodigo(), usuario.getTipoUsuario(), usuario.getTipoDocumento(), usuario.getNumeroDocumento(),
				usuario.getNombres(), usuario.getApellidos(), usuario.getCelular(), usuario.getDireccion(), usuario.getEdad(), usuario.getEmail(), usuario.getUsuario(), usuario.getContrasenna(),
				usuario.getEliminado(), usuario.getFechaRegistro(), usuario.getFechaModificacion());
	}

}
