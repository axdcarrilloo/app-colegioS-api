package com.casa.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmarMapResponse {
	
	private ArmarMapResponse() {}
	
	private static Logger log = LoggerFactory.getLogger(ArmarMapResponse.class);
	
	public static String armarRegistroFallidoUsuario(Map<String, Object> map) {
		log.info("ArmarMapResponse.class : armarMap() -> Armando String en registro fallido");

			String response = "Error en el registro, campos vacios (";
			Integer maxCaracteres = response.length();
			
			if(map.get("tipoUsuario") != null) {
				response = response + map.get("tipoUsuario");
			}
			if(map.get("tipoDocumento") != null) {
				if(response.length() == maxCaracteres) {
					response = response + map.get("tipoDocumento");
				} else {
					response = response +", "+ map.get("tipoDocumento");
				}
			}
			if(map.get("numeroDocumento") != null) {
				if(response.length() == maxCaracteres) {
					response = response + map.get("numeroDocumento");
				} else {
					response = response +", "+ map.get("numeroDocumento");
				}
			}
			if(map.get("apellidos") != null) {
				if(response.length() == maxCaracteres) {
					response = response + map.get("apellidos");
				} else {
					response = response +", "+ map.get("apellidos");
				}
			}
			if(map.get("celular") != null) {
				if(response.length() == maxCaracteres) {
					response = response + map.get("celular");
				} else {
					response = response +", "+ map.get("celular");
				}
			}
			if(map.get("usuario") != null) {
				if(response.length() == maxCaracteres) {
					response = response + map.get("usuario");
				} else {
					response = response +", "+ map.get("usuario");
				}
			}
			if(map.get("contrasenna") != null) {
				if(response.length() == maxCaracteres) {
					response = response + map.get("contrasenna");
				} else {
					response = response +", "+ map.get("contrasenna");
				}
			}
			response = response + ")";
			return response;
	}
}
