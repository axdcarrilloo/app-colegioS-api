package com.casa.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CodigoEntityTest {
	
	@Test
	void validarCaracteristicas() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		CodigoEntity codigo = new CodigoEntity(12L, rol, "EST", 3, "Codigos para estudiantes", false, 
				fechaCreacion, fechaCreacion);
		
		assertNotNull(codigo.getRol());
		assertEquals(12L, codigo.getId());
		assertFalse(codigo.getEliminado());
		assertEquals(2009, codigo.getFechaRegistro().getYear());
	}

}
