package com.casa.domain.mappers;

import com.casa.domain.dtos.RolRegistrarDto;
import com.casa.domain.entities.RolEntity;
import com.casa.utils.Constantes;

public class RolMapper {

    public static RolEntity convertirDtoAEntity(RolRegistrarDto rol) {
        return new RolEntity(0L, rol.getCodigo(), rol.getNombre(), false, Constantes.obtenerFechaActual(), Constantes.obtenerFechaActual());
    }

}
