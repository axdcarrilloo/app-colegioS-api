package com.casa.feignclient;

import com.casa.feignclient.dtos.MateriaRegistrarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.casa.feignclient.dtos.RespuestaPrincipalDto;
import com.casa.utils.RouteFeign;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "materia", url = "${feign.client.horario}")
public interface MateriaFeign {

	@PostMapping(value = RouteFeign.MATERIA + RouteFeign.REGISTRAR)
	RespuestaPrincipalDto registrar(@RequestBody MateriaRegistrarDto materia);
	
	@GetMapping(value = RouteFeign.MATERIA + RouteFeign.CONSULTARPOR_NOMBRE)
	RespuestaPrincipalDto consultarPorNombre(@PathVariable String nombre);
	
	@GetMapping(value = RouteFeign.MATERIA + RouteFeign.CONSULTAR_TAS)
	RespuestaPrincipalDto consultarTodas();

}
