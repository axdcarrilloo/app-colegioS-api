package com.casa.services;

import com.casa.domain.entities.RolEntity;
import com.casa.domain.dtos.RolRegistrarDto;
import com.casa.domain.mappers.RolMapper;
import com.casa.repositories.RolRepository;
import com.casa.utils.Constantes;
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
        return rol.getNombre().isEmpty();
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
            map.put(Constantes.MAP_ERROR_CAMPOS_VACIOS, Constantes.MSG_CAMPOS_VACIOS);
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
