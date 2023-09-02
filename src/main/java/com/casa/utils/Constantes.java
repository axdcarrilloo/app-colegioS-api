package com.casa.utils;

import java.time.LocalDateTime;

public class Constantes {
	
	private Constantes() {}

	public static final String MAP_ERROR_CAMPOS_VACIOS = "errorCamposVacios";
	public static final String MAP_ERROR_NOPREFIJO = "errorNoPrefijo";
	public static final String MAP_ERROR_SIEXISTENCIA = "errorSiExiste";
	public static final String MAP_RESPONSE = "response";
	public static final String MAP_ERROR_NOEXISTENCIA = "errorNoExiste";
	public static final String MSG_CAMPOS_VACIOS = "Campos vacios, por favor validar";
	public static final String MSG_SIEXISTENCIA = "Ya se encuentra registrado";
	public static final String MSG_NOEXISTENCIA = "No se encuentra registrado";

	public static final String TTL_CONSULTA_EXITOSA = "Consulta Exitosa";
	public static final String TTL_CONSULTA_FALLIDA = "Consulta Fallida";
	public static final String TTL_REGISTRO_FALLIDO = "Registro Fallido";
	public static final String TTL_REGISTRO_EXITOSO = "Registro Exitoso";
	public static final String TTL_ELIMINACION_EXITOSA = "Eliminacion Exitosa";
	public static final String TTL_ELIMINACION_FALLIDA = "Eliminacion Fallida";
	public static final String TTL_MODIFICACION_EXITOSA = "Modificacion Exitosa";
	public static final String TTL_MODIFICACION_FALLIDA = "Modificacion Fallida";
	public static final int HTTP_NOENCONTRADO = 404;
	public static LocalDateTime obtenerFechaActual() {
		return LocalDateTime.now();
	} 

}
