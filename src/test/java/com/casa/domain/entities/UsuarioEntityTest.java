package com.casa.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioEntityTest {
	
	@Test
	void validarCaracteristicas() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		UsuarioEntity usuario = new UsuarioEntity(8L, "PRF004", "02", "01", "1045121734", "Ronald", "Teran",
				"399517511", "Ciudad#45", "rote02", "Rote123", false, fechaCreacion, fechaCreacion);
		
		assertNotNull(usuario);
		assertEquals(8L, usuario.getId());
		assertEquals(2009, usuario.getFechaRegistro().getYear());
		assertEquals("1045121734", usuario.getNumeroDocumento());
		assertFalse(usuario.getEliminado());
	}

}
