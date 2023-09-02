package com.casa.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casa.domain.entities.UsuarioEntity;
import com.casa.utils.ConstantesSQL;

import javax.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	Optional<UsuarioEntity> findByNumeroDocumento(String numeroDocumento);
	
	@Transactional
	@Modifying
	@Query(value = ConstantesSQL.MODIFICAR_USUARIO, nativeQuery = true)
	void modificarTodo(@Param("id")Long id, @Param("tipoUsuario")String tipoUsuario, @Param("tipoDocumento")String tipoDocumento, 
			@Param("numeroDocumento")String numeroDocumento, @Param("nombres")String nombres, @Param("apellidos")String apellidos, 
			@Param("celular")String celular, @Param("direccion")String direccion, @Param("usuario")String usuario, 
			@Param("fechaModificacion")LocalDateTime fechaModificacion);
		
	List<UsuarioEntity> findByEliminado(Boolean eliminado);
	
	@Transactional
	@Modifying
	@Query(value = ConstantesSQL.ELIMINAR_USUARIO_LOGICAMENTE, nativeQuery = true)
	void eliminacionLogica(@Param("id")Long id, @Param("eliminado")Boolean eliminado);
		
	Optional<UsuarioEntity> findById(Long id);

}
