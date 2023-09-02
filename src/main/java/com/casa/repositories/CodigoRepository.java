package com.casa.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.casa.domain.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.CodigoEntity;
import com.casa.utils.ConstantesSQL;

import javax.transaction.Transactional;

@Repository
public interface CodigoRepository extends JpaRepository<CodigoEntity, Long> {

	CodigoEntity findByRol(RolEntity rol);
	
	List<CodigoEntity> findByEliminado(Boolean eliminado);
	
	Optional<CodigoEntity> findByPrefijo(String prefijo);
	
	@Modifying
    @Transactional
    @Query(value = ConstantesSQL.ELIMINAR_CODIGO_LOGICAMENTE, nativeQuery = true)
	void eliminacionLogica(@Param("id")Long id, @Param("eliminado")Boolean eliminado);

	@Modifying
    @Transactional
    @Query(value = ConstantesSQL.MODIFICAR_CODIGO, nativeQuery = true)
	Integer modificar(@Param("id")Long id, @Param("prefijo")String prefijo, @Param("consecutivo")Integer consecutivo, 
			@Param("descripcion")String descripcion, @Param("fechaModificacion")LocalDateTime fechaModificacion);
}
