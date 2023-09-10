package com.casa.services;

import com.casa.domain.entities.RolEntity;
import com.casa.repositories.RolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolSvc;

    private RolEntity obtenerRol() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        return new RolEntity(10L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);
    }

    private List<RolEntity> obtenerRoles() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2009, 11, 15, 10, 20, 5);
        RolEntity rol1 = new RolEntity(8L, "01", "Estudiante", false, fechaCreacion, fechaCreacion);

        fechaCreacion = LocalDateTime.of(2009, 11, 15, 8, 15, 14);
        RolEntity rol2 = new RolEntity(12L, "02", "Profesor", false, fechaCreacion, fechaCreacion);

        fechaCreacion = LocalDateTime.of(2009, 11, 16, 9, 1, 3);
        RolEntity rol3 = new RolEntity(16L, "03", "Coordinador", false, fechaCreacion, fechaCreacion);

        List<RolEntity> roles = new ArrayList<>();
        roles.add(rol1);
        roles.add(rol2);
        roles.add(rol3);
        return roles;
    }

    @Test
    void consultarPorCodigoTestError() {
        RolEntity rolVacio = new RolEntity();
        Mockito.when(rolRepository.findByCodigo("01")).thenReturn(rolVacio);

        RolEntity rol = rolSvc.consultarPorCodigo("01");

        Assertions.assertNull(rol.getCodigo());
        Assertions.assertNull(rol.getNombre());
        Assertions.assertNull(rol.getEliminado());
        Assertions.assertNull(rol.getFechaRegistro());
    }

    @Test
    void consultarPorCodigoTestExito() {
        Mockito.when(rolRepository.findByCodigo("01")).thenReturn(obtenerRol());

        RolEntity rol = rolSvc.consultarPorCodigo("01");

        Assertions.assertNotNull(rol);
        Assertions.assertFalse(rol.getEliminado());
        Assertions.assertEquals("Estudiante", rol.getNombre());
        Assertions.assertNotNull(rol.getFechaRegistro());
    }

    @Test
    void consultarTodosTest() {
        Mockito.when(rolRepository.findAll()).thenReturn(obtenerRoles());

        List<RolEntity> roles = rolSvc.consultarTodos();

        Assertions.assertEquals(3, roles.size());
        Assertions.assertFalse(roles.isEmpty());
        Assertions.assertEquals(12L, roles.get(1).getId());
    }

    @Test
    void consultarPorIdTestError() {
        RolEntity rolVacio = new RolEntity();
        Optional<RolEntity> optional = Optional.of(rolVacio);
        Mockito.when(rolRepository.findById(2L)).thenReturn(optional);

        RolEntity rol = rolSvc.consultarPorId(2L);

        Assertions.assertNull(rol.getId());
        Assertions.assertNull(rol.getNombre());
        Assertions.assertNull(rol.getCodigo());
        Assertions.assertNull(rol.getFechaRegistro());
    }

    @Test
    void consultarPorIdTestExito() {
        Optional<RolEntity> optional = Optional.of(obtenerRol());
        Mockito.when(rolRepository.findById(10L)).thenReturn(optional);

        RolEntity rol = rolSvc.consultarPorId(10L);

        Assertions.assertNotNull(rol);
        Assertions.assertEquals(10L, rol.getId());
        Assertions.assertEquals("01", rol.getCodigo());
    }

}
