package com.casa.services;

import com.casa.domain.entities.RolEntity;
import com.casa.domain.dtos.RolRegistrarDto;
import com.casa.domain.mappers.RolMapper;
import com.casa.repositories.RolRepository;
import com.casa.utils.Constantes;
import com.casa.utils.MensajesProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RolService {

    private final Logger log = LoggerFactory.getLogger(CodigoService.class);

    @SuppressWarnings("unused")
    @Autowired
    private RolRepository rolRepository;

    private Boolean validarCamposVacios(RolRegistrarDto rol) {
        log.info("RolService.class : validarCamposVacios() -> Validando campos vacios...!");
        if(rol == null) {
            return true;
        }
        if(rol.getCodigo() == null || rol.getCodigo().isEmpty()) {
            return true;
        }
        return rol.getNombre() == null || rol.getNombre().isEmpty();
    }

    public Map<String, Object> consultarPorIdParaController(Long id) {
        Map<String, Object> map = new HashMap<>();
        RolEntity rol = consultarPorId(id);
        if(rol != null) {
            map.put(Constantes.MAP_RESPONSE, rol);
        } else {
            map.put(Constantes.MAP_ERROR_NOEXISTENCIA, MensajesProperties.MSG_NOEXISTENCIA);
        }
        return map;
    }

    public Boolean existenciaPorCodigo(String codigo) {
        log.info("RolService.class : existenciaPorCodigo() -> Validando existencia por codigo...!");
        return consultarPorCodigo(codigo) != null;
    }

    public RolEntity consultarPorCodigo(String codigo) {
        log.info("RolService.class : consultarPorCodigo() -> Consultando por codigo...!");
        return rolRepository.findByCodigo(codigo);
    }

    public List<RolEntity> consultarTodos() {
        log.info("RolService.class : consultarTodos() -> Consultando todos los roles...!");
        return rolRepository.findAll();
    }

    public Map<String, Object> registrar(RolRegistrarDto rol) {
        Map<String, Object> map = new HashMap<>();
        if(validarCamposVacios(rol)) {
            map.put(Constantes.MAP_ERROR_CAMPOS_VACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
            return map;
        }
        if(existenciaPorCodigo(rol.getCodigo())) {
            map.put(Constantes.MAP_ERROR_SIEXISTENCIA, MensajesProperties.MSG_SIEXISTENCIA);
        } else {
            log.info("RolService.class : registrar() -> Registrando rol...!");
            map.put(Constantes.MAP_RESPONSE, rolRepository.save(RolMapper.convertirDtoAEntity(rol)).getId());
        }
        return map;
    }

    public RolEntity consultarPorId(Long id) {
        log.info("RolService.class : consultarPorId() -> Consultando rol por id...!");
        Optional<RolEntity> codigoOpt = rolRepository.findById(id);
        return codigoOpt.orElse(null);
    }

}
