package com.casa.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casa.domain.dtos.UsuarioExisteLogicoDto;
import com.casa.domain.dtos.UsuarioRegistrarDto;
import com.casa.domain.entities.UsuarioEntity;

@ExtendWith(MockitoExtension.class)
public class UsuarioMapperTest {
	
	@Test
	void convertirDeListEntityToListDtoTest() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		UsuarioEntity usuario1 = new UsuarioEntity(11L, "PRF004", "02", "01", "1045121734", "Ronald", "Teran",
				"399517511", "Ciudad#45", "rote02", "Rote123", false, fechaCreacion, fechaCreacion);
		
		fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 34, 43);
		UsuarioEntity usuario2 = new UsuarioEntity(12L, "EST003", "01", "02", "1045121290", "Steven", "Duarte",
				"3991213477", "Ciudad#40", "Stdu14", "venDu13", false, fechaCreacion, fechaCreacion);
		
		fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 40, 3);
		UsuarioEntity usuario3 = new UsuarioEntity(12L, "EST005", "01", "02", "10451212875", "Yerson", "Anaya",
				"3966735033", "Ciudad#12", "yeran", "Yeran23", false, fechaCreacion, fechaCreacion);
		
		List<UsuarioEntity> usuarios = new ArrayList<>();
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		usuarios.add(usuario3);
		
		List<UsuarioExisteLogicoDto> usuariosLogico = UsuarioMapper.convertirDeListEntityToListDto(usuarios);
		
		assertFalse(usuariosLogico.isEmpty());
		assertEquals(3, usuariosLogico.size());
		assertEquals("1045121290", usuariosLogico.get(1).getNumeroDocumento());
	}
	
	@Test
	void convertirDeDtoToEntityTest() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		UsuarioRegistrarDto usuarioDto = new UsuarioRegistrarDto("PRF004", "02", "01", "1045121734", "Ronald", "Teran",
				"399517511", "Ciudad#45", "rote02", "Rote123", false, fechaCreacion, fechaCreacion);
		
		UsuarioEntity usuario = UsuarioMapper.convertirDeDtoToEntity(usuarioDto);
		
		assertNotNull(usuario);
		assertEquals(0L, usuario.getId());
		assertEquals(2009, usuario.getFechaRegistro().getYear());
		assertEquals("1045121734", usuario.getNumeroDocumento());
	}

}
