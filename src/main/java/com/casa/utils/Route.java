package com.casa.utils;

public class Route {
	
	private Route() {}
	
	public static final String COLEGIOS = "/Colegios";
	public static final String CODIGO = "/Codigo";
	public static final String USUARIO = "/Usuario";
	public static final String NOTA = "/Nota";
	public static final String ROL = "/Rol";
	
//	Crud URLs
	public static final String CONSULTAR_POR_ID = "/ConsultarPorId/{id}";
	public static final String CONSULTAR_POR_NOMBRE = "/ConsultarEstudiantePorNombre/{nombres}";
	public static final String TODOS_ESTUDIANTES = "/TodosEstudiantes";
	public static final String REGISTRAR = "/Registrar";
	public static final String TODOS = "/Todos";
	public static final String TODAS = "/Todas";
	public static final String TODOS_EXISTENTES = "/TodosExistentes";
	public static final String ELIMINAR = "/Eliminar/{id}";
	public static final String ELIMINACION_LOGICA = "/EliminacionLogica/{id}";
	public static final String MODIFICAR = "/Modificar";
	
}
