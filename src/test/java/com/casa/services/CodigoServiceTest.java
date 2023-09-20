package com.casa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casa.domain.dtos.CodigoExisteLogicoDto;
import com.casa.domain.dtos.CodigoRegistrarDto;
import com.casa.domain.entities.CodigoEntity;
import com.casa.domain.entities.RolEntity;
import com.casa.repositories.CodigoRepository;
import com.casa.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class CodigoServiceTest {
	
	@Mock
	private CodigoRepository codigoRepository;
	
	@Mock
	private RolService rolSvc;
	
	@InjectMocks
	private CodigoService codigoSvc;
	
	private List<CodigoEntity> obtenerCodigos() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
        CodigoEntity codigoEstudiante = new CodigoEntity(13L, rol, "EST", 4, "Codigo para estudiantes", false, fechaCreacion, fechaCreacion);
        
        rol = new RolEntity(12L, "02", "Profesor", false, fechaCreacion, fechaCreacion);
		CodigoEntity codigoProfesor = new CodigoEntity(14L, rol, "PRF", 2, "Codigos para profesores", false, 
				fechaCreacion, fechaCreacion);
		
		rol = new RolEntity(13L, "03", "Coordinador", false, fechaCreacion, fechaCreacion);
		CodigoEntity codigoCoordinador = new CodigoEntity(10L, rol, "COO", 2, "Codigos para coordinadores", false, 
				fechaCreacion, fechaCreacion);
        
        List<CodigoEntity> codigos = new ArrayList<>();
        codigos.add(codigoEstudiante);
        codigos.add(codigoProfesor);
        codigos.add(codigoCoordinador);
        return codigos;
    }
	
	private CodigoEntity obtenerCodigo() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
        return new CodigoEntity(13L, rol, "EST", 4, "Codigo para estudiantes", false, fechaCreacion, fechaCreacion);
    }
	
	@Test
	void existenciaPorRolTestNoExistencia() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		when(codigoRepository.findByRol(rol)).thenReturn(null);
		
		Boolean validaCodigo = codigoSvc.existenciaPorRol(rol);
		
		assertNotNull(validaCodigo);
		assertFalse(validaCodigo);
	}
	
	@Test
	void existenciaPorRolTestExito() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		when(codigoRepository.findByRol(rol)).thenReturn(obtenerCodigo());
		
		Boolean validaCodigo = codigoSvc.existenciaPorRol(rol);
		
		assertNotNull(validaCodigo);
		assertTrue(validaCodigo);
	}
	
	@Test
	void consultarPorRolTestError() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		when(codigoRepository.findByRol(rol)).thenReturn(null);
		
		CodigoEntity codigo = codigoSvc.consultarPorRol(rol);
		
		assertNull(codigo);
	}
	
	@Test
	void consultarPorRolTestExito() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		when(codigoRepository.findByRol(rol)).thenReturn(obtenerCodigo());
		
		CodigoEntity codigo = codigoSvc.consultarPorRol(rol);
		
		assertNotNull(codigo);
		assertEquals(13L, codigo.getId());
		assertFalse(codigo.getEliminado());
		assertEquals(2009, codigo.getFechaRegistro().getYear());
	}
	
	@Test
	void asignarCodigoTestErrorNoExistencia() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(8L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		when(rolSvc.consultarPorCodigo("01")).thenReturn(rol);
		when(codigoRepository.findByRol(rol)).thenReturn(null);
		
		String codigo = codigoSvc.asignarCodigo("01");
		
		assertNull(codigo);
	}
	
	@Test
	void asignarCodigoTestErrorNoExistenciaRol() {
		when(rolSvc.consultarPorCodigo("01")).thenReturn(null);
		
		String codigo = codigoSvc.asignarCodigo("01");
		
		assertNull(codigo);
	}
	
	@Test
	void asignarCodigoTestExito() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(8L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
		when(rolSvc.consultarPorCodigo("01")).thenReturn(rol);
		when(codigoRepository.findByRol(rol)).thenReturn(obtenerCodigo());
		Optional<CodigoEntity> optional = Optional.of(obtenerCodigo());
		when(codigoRepository.findByPrefijo("EST")).thenReturn(optional);
		when(codigoRepository.findById(13L)).thenReturn(optional);
		when(codigoRepository.modificar(anyLong(), anyString(), anyInt(), anyString(), any())).thenReturn(1);
		
		String codigo = codigoSvc.asignarCodigo("01");
		
		assertNotNull(codigo);
		assertEquals("EST004", codigo);
	}
	
	@Test
	void consultarPorIdTestError() {
		Optional<CodigoEntity> optional = Optional.empty();
		when(codigoRepository.findById(15L)).thenReturn(optional);
		
		CodigoEntity codigo = codigoSvc.consultarPorId(15L);
		
		assertNull(codigo);
	}
	
	@Test
	void consultarPorIdTestExito() {
		Optional<CodigoEntity> optional = Optional.of(obtenerCodigo());
		when(codigoRepository.findById(13L)).thenReturn(optional);
		
		CodigoEntity codigo = codigoSvc.consultarPorId(13L);
		
		assertNotNull(codigo);
		assertEquals(13L, codigo.getId());
		assertFalse(codigo.getEliminado());
		assertEquals(2009, codigo.getFechaRegistro().getYear());
	}
	
	@Test
	void consultarPorPrefijoTestError() {
		Optional<CodigoEntity> optional = Optional.empty();
		when(codigoRepository.findByPrefijo("EST")).thenReturn(optional);
		
		CodigoEntity codigo = codigoSvc.consultarPorPrefijo("EST");
		
		assertNull(codigo);
	}
	
	@Test
	void consultarPorPrefijoTestExito() {
		Optional<CodigoEntity> optional = Optional.of(obtenerCodigo());
		when(codigoRepository.findByPrefijo("EST")).thenReturn(optional);
		
		CodigoEntity codigo = codigoSvc.consultarPorPrefijo("EST");
		
		assertNotNull(codigo);
		assertEquals(13L, codigo.getId());
		assertFalse(codigo.getEliminado());
		assertEquals(2009, codigo.getFechaRegistro().getYear());
	}
	
	@Test
	void consultarExistentesTest() {
		when(codigoRepository.findByEliminado(false)).thenReturn(obtenerCodigos());
		
		List<CodigoExisteLogicoDto> codigosExistentes = codigoSvc.consultarExistentes();
		
		assertEquals(3, codigosExistentes.size());
		assertEquals(14L, codigosExistentes.get(1).getId());
	}
	
	@Test
	void consultarTodosTest() {
		when(codigoRepository.findAll()).thenReturn(obtenerCodigos());
		
		List<CodigoEntity> codigos = codigoSvc.consultarTodos();
		
		assertEquals(3,  codigos.size());
		assertEquals(14L, codigos.get(1).getId());
	}
	
	@Test
	void registrarTestErrorDuplicidad() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
        CodigoRegistrarDto codigoDto = new CodigoRegistrarDto(rol, "EST", 4, "Codigo para estudiantes",
        		false, fechaCreacion, fechaCreacion);
        
        when(rolSvc.consultarPorId(10L)).thenReturn(rol);
        when(codigoRepository.findByRol(rol)).thenReturn(obtenerCodigo());
        
        Map<String, Object> map = codigoSvc.registrar(codigoDto);
        
        assertNull(map.get(Constantes.MAP_RESPONSE));
	}
	
	@Test
	void registrarTestErrorInexistenciaRol() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
        CodigoRegistrarDto codigoDto = new CodigoRegistrarDto(rol, "EST", 4, "Codigo para estudiantes",
        		false, fechaCreacion, fechaCreacion);
        
        when(rolSvc.consultarPorId(10L)).thenReturn(null);
        
        Map<String, Object> map = codigoSvc.registrar(codigoDto);
        
        assertNull(map.get(Constantes.MAP_RESPONSE));
	}
	
	@Test
	void registrarTestExito() {
		LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol = new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
        CodigoRegistrarDto codigoDto = new CodigoRegistrarDto(rol, "EST", 4, "Codigo para estudiantes",
        		false, fechaCreacion, fechaCreacion);
        
        when(rolSvc.consultarPorId(10L)).thenReturn(rol);
        when(codigoRepository.findByRol(rol)).thenReturn(null);
        when(codigoRepository.save(any())).thenReturn(obtenerCodigo());
        
        Map<String, Object> map = codigoSvc.registrar(codigoDto);
        
        assertNotNull(map.get(Constantes.MAP_RESPONSE));
        assertEquals(13L, (Long)map.get(Constantes.MAP_RESPONSE));
	}

}
