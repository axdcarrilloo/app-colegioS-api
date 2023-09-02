package com.casa.controllers;

import com.casa.services.RolService;
import com.casa.domain.dtos.ResponseMainDto;
import com.casa.domain.dtos.RolRegistrarDto;
import com.casa.utils.Constantes;
import com.casa.utils.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = Route.COLEGIOS + Route.ROL, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class RolController {

    @SuppressWarnings("unused")
    @Autowired
    private RolService rolSVC;

    @GetMapping(value = Route.TODOS)
    public ResponseEntity<ResponseMainDto> consultarTodos() {
        return new ResponseEntity<>(new ResponseMainDto
                (Constantes.TTL_CONSULTA_EXITOSA, rolSVC.consultarTodos()), HttpStatus.OK);
    }

    @PostMapping(value = Route.REGISTRAR)
    public ResponseEntity<ResponseMainDto> registrar(@RequestBody RolRegistrarDto rol) {
        Map<String, Object> map = rolSVC.registrar(rol);
        String errorCamposVacios = (String)map.get(Constantes.MAP_ERROR_CAMPOS_VACIOS);
        if(errorCamposVacios != null) {
            return new ResponseEntity<>(new ResponseMainDto
                    (Constantes.TTL_REGISTRO_FALLIDO, errorCamposVacios), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ResponseMainDto
                    (Constantes.TTL_REGISTRO_EXITOSO, map.get(Constantes.MAP_RESPONSE)), HttpStatus.CREATED);
        }
    }

}
