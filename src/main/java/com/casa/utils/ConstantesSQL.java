package com.casa.utils;

public class ConstantesSQL {
	
	private ConstantesSQL()  {}
	
	public static final String MODIFICAR_CODIGO = "UPDATE `codigos` SET `prefijo` = :prefijo, `consecutivo` = :consecutivo, "
			+ "`descripcion` = :descripcion, `fecha_modificacion` = :fechaModificacion WHERE `codigos`.`id` = :id";
	
	public static final String ELIMINAR_ASIGNATURA_LOGICAMENTE = "UPDATE `asignaturas` SET `eliminado` = :eliminado WHERE `asignaturas`.`id` = :id";
	
	public static final String ELIMINAR_USUARIO_LOGICAMENTE = "UPDATE `usuarios` SET `eliminado` = :eliminado WHERE `usuarios`.`id` = :id";
	
	public static final String ELIMINAR_CODIGO_LOGICAMENTE = "UPDATE `codigos` SET `eliminado` = :eliminado WHERE `codigos`.`id` = :id";
	
	public static final String MODIFICAR_CURSO = "UPDATE `cursos` SET `curso` = :curso, `fecha_modificacion` = :fechaModificacion, "
			+ "`hora_modificacion` = :horaModificacion WHERE `cursos`.`id` = :id";
	
	public static final String ELIMINAR_CURSO_LOGICAMENTE = "UPDATE `cursos` SET `eliminado` = :eliminado WHERE `cursos`.`id` = :id";
	
	public static final String MODIFICAR_ASIGNATURA = "UPDATE `asignaturas` SET `nombre` = :nombre, `fecha_modificacion` = :fechaModificacion, "
			+ "`hora_modificacion` = :horaModificacion WHERE `asignaturas`.`id` = :id";
	
	public static final String MODIFICAR_USUARIO = "UPDATE `usuarios` SET `tipo_usuario` = :tipoUsuario, `tipo_documento` = :tipoDocumento, "
			+ "`numero_documento` = :numeroDocumento, `nombres` = :nombres, `apellidos` = :apellidos, `celular` = :celular, `direccion` = :direccion, "
			+ "`usuario` = :usuario, `fecha_modificacion` = :fechaModificacion WHERE `usuarios`.`id` = :id";

}
