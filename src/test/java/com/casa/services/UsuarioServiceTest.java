package com.casa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casa.domain.dtos.UsuarioRegistrarDto;
import com.casa.domain.entities.UsuarioEntity;
import com.casa.repositories.UsuarioRepository;
import com.casa.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private CodigoService codigoSvc;
	
	@InjectMocks
	private UsuarioService usuarioSvc;
		
	private UsuarioEntity obtenerUsuario() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		return new UsuarioEntity(14L, "EST004", "01", "02", "1045121734", "Ronald", "Teran",
				"399517511", "Ciudad#45", "rote02", "Rote123", false, fechaCreacion, fechaCreacion);
	}
	
	@Test
	void registrarTestExito() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		UsuarioRegistrarDto usuarioDto = new UsuarioRegistrarDto(null, "01", "02", "1045121734", "Ronald", "Teran",
				"399517511", "Ciudad#45", "rote02", "Rote123", false, fechaCreacion, fechaCreacion);
		
		Optional<UsuarioEntity> optional = Optional.empty();
		when(usuarioRepository.findByNumeroDocumento("1045121734")).thenReturn(optional);
		when(codigoSvc.asignarCodigo("01")).thenReturn("PRF004");
		when(usuarioRepository.save(any())).thenReturn(obtenerUsuario());
		
		Map<String, Object> map = usuarioSvc.registrar(usuarioDto);
		
		assertNotNull(map.get(Constantes.MAP_RESPONSE));
		assertEquals(14L, (Long)map.get(Constantes.MAP_RESPONSE));
	}

}
