package com.casa.utils;

public class RouteFeign {
	
	private RouteFeign() {}
	
	public static final String MATERIA = "/Materia";
	public static final String PROFESOR = "/Profesor";
	
	public static final String CONSULTARPOR_NOMBRE = "/ConsultarPorNombre/{nombre}";
	public static final String CONSULTAR_TAS = "/Todas";
	public static final String REGISTRAR = "/Registrar";
	public static final String CONSULTAR_TOS = "/Todos";
	public static final String CONSULTAR_PORID = "/ConsultarPorId/{id}";

}
