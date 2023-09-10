package com.casa.domain.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class RolEntityTest {

    @Test
    void validarCaracteristicas() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);

        assertEquals(10L, rol.getId());
        assertEquals("01", rol.getCodigo());
        assertEquals("Estudiante", rol.getNombre());
        assertFalse(rol.getEliminado());
    }

}
