package com.casa.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class RolEntityTest {

    @Test
    void validarCaracteristicas() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);

        Assertions.assertEquals(10L, rol.getId());
        Assertions.assertEquals("01", rol.getCodigo());
        Assertions.assertEquals("Estudiante", rol.getNombre());
        Assertions.assertFalse(rol.getEliminado());
    }

}
