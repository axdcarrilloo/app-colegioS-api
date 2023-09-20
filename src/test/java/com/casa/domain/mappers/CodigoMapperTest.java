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

import com.casa.domain.dtos.CodigoExisteLogicoDto;
import com.casa.domain.dtos.CodigoRegistrarDto;
import com.casa.domain.entities.CodigoEntity;
import com.casa.domain.entities.RolEntity;

@ExtendWith(MockitoExtension.class)
public class CodigoMapperTest {
	
	@Test
	void convertirDeListEntityToListDtoTest() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		CodigoEntity codigoEstudiante = new CodigoEntity(9L, rol, "EST", 2, "Codigos para estudiantes", false, 
				fechaCreacion, fechaCreacion);
		
		rol = new RolEntity(12L, "02", "Profesor", false, fechaCreacion, fechaCreacion);
		CodigoEntity codigoProfesor = new CodigoEntity(10L, rol, "PRF", 2, "Codigos para profesores", false, 
				fechaCreacion, fechaCreacion);
		
		rol = new RolEntity(13L, "03", "Coordinador", false, fechaCreacion, fechaCreacion);
		CodigoEntity codigoCoordinador = new CodigoEntity(10L, rol, "COO", 2, "Codigos para coordinadores", false, 
				fechaCreacion, fechaCreacion);
		
		List<CodigoEntity> codigos = new ArrayList<>();
		codigos.add(codigoEstudiante);
		codigos.add(codigoProfesor);
		codigos.add(codigoCoordinador);
		List<CodigoExisteLogicoDto> codigoExistentesLogicos = CodigoMapper.convertirDeListEntityToListDto(codigos);
		
		assertEquals(3, codigoExistentesLogicos.size());
		assertNotNull(codigoExistentesLogicos.get(1).getFechaRegistro());
	}
	
	@Test
	void convertirDeDtoToEntityTest() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
		RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		CodigoRegistrarDto codigoDto = new CodigoRegistrarDto(rol, "EST", 2, "Codigos para estudiantes", false, 
				fechaCreacion, fechaCreacion);
		
		CodigoEntity codigo = CodigoMapper.convertirDeDtoToEntity(codigoDto);
		
		assertNotNull(codigo);
		assertNotNull(codigo.getRol());
		assertEquals(0L, codigo.getId());
		assertFalse(codigo.getEliminado());
	}

}
