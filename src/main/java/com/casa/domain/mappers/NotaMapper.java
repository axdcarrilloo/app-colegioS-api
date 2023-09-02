package com.casa.domain.mappers;

import com.casa.domain.dtos.NotaRegistrarDto;
import com.casa.domain.entities.NotaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotaMapper {
	
	private static Logger log = LoggerFactory.getLogger(NotaMapper.class);
	
	private NotaMapper() {}
	
	public static NotaEntity convertirDtoToEntity(NotaRegistrarDto nota) {
		log.info("NotaMapper.class : convertirDtoToEntity() -> Convirtiendo de NotaRegistrarDto a NotaEntity...!");
		return new NotaEntity(0l, nota.getNota(), nota.getEstudiante(), nota.getProfesor(), nota.getFechaRegistro(),
				nota.getFechaModificacion());
	}

}
