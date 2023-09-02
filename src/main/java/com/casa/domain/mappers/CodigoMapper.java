package com.casa.domain.mappers;

import java.util.ArrayList;
import java.util.List;

import com.casa.domain.dtos.CodigoExisteLogicoDto;
import com.casa.domain.dtos.CodigoRegistrarDto;
import com.casa.domain.entities.CodigoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodigoMapper {
	
	private static Logger log = LoggerFactory.getLogger(CodigoMapper.class);
	
	private CodigoMapper() {}
	
	public static List<CodigoExisteLogicoDto> convertirDeListEntityToListDto(List<CodigoEntity> codigos) {
		List<CodigoExisteLogicoDto> codigosResponse = new ArrayList<>();
		for (CodigoEntity c : codigos) {
			codigosResponse.add( new CodigoExisteLogicoDto(c.getId(), c.getPrefijo(), c.getConsecutivo(), c.getDescripcion(),
					c.getFechaRegistro(), c.getFechaModificacion()) );
		}
		return codigosResponse;
	}
	
	 public static CodigoEntity convertirDeDtoToEntity(CodigoRegistrarDto codigoDto) {
		log.info("CodigoMapper.class : convertirDeDtoToEntity() -> Convirtiendo de CodigoRegistrarDto a CodigoEntity...!");
		return new CodigoEntity(0l, codigoDto.getRol(),codigoDto.getPrefijo(), codigoDto.getConsecutivo(), codigoDto.getDescripcion(), codigoDto.getEliminado(),
				codigoDto.getFechaRegistro(), codigoDto.getFechaModificacion());
	}

}
