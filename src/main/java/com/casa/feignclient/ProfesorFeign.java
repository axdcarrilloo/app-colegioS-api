package com.casa.feignclient;

import com.casa.feignclient.dtos.ProfesorRegistrarDto;
import com.casa.feignclient.dtos.RespuestaPrincipalDto;
import com.casa.utils.RouteFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profesor", url = "${feign.client.horario}")
public interface ProfesorFeign {

    @PostMapping(value = RouteFeign.PROFESOR + RouteFeign.REGISTRAR)
    RespuestaPrincipalDto registrar(@RequestBody ProfesorRegistrarDto profesor);

    @GetMapping(value = RouteFeign.PROFESOR + RouteFeign.CONSULTAR_PORID)
    RespuestaPrincipalDto consultarPorId(@PathVariable String id);

    @GetMapping(value = RouteFeign.PROFESOR + RouteFeign.CONSULTAR_TOS)
    RespuestaPrincipalDto consultarTodos();

}
