package com.casa.utils;

import java.time.LocalDateTime;

public class Constantes {
	
	private Constantes() {}

	public static final String CODIGO_ROL_ASIGNATURAS = "06";

	public static final String MAP_ERROR_CAMPOS_VACIOS = "errorCamposVacios";
	public static final String MAP_ERROR_NOPREFIJO = "errorNoPrefijo";
	public static final String MAP_ERROR_SIEXISTENCIA = "errorSiExiste";
	public static final String MAP_ERROR_NOEXISTENCIA = "errorNoExiste";

	public static final String MAP_RESPONSE = "response";
	public static final String MAP_ERRORR_SERVER_HORARIOS = "errorServerHorarios";

	public static final int HTTP_NOENCONTRADO = 404;

	public static LocalDateTime obtenerFechaActual() {
		return LocalDateTime.now();
	} 

}
