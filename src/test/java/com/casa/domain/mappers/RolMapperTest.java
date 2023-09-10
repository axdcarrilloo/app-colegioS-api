package com.casa.domain.mappers;

import com.casa.domain.dtos.RolRegistrarDto;
import com.casa.domain.entities.RolEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RolMapperTest {

    @Test
    void convertirDtoAEntityTest() {
        RolRegistrarDto rolDto = new RolRegistrarDto("01", "Estudiante");

        RolEntity rol = RolMapper.convertirDtoAEntity(rolDto);

        Assertions.assertEquals(0L, rol.getId());
        Assertions.assertFalse(rol.getEliminado());
        Assertions.assertNotNull(rol.getFechaRegistro());
        Assertions.assertEquals("01", rol.getCodigo());
    }

}
