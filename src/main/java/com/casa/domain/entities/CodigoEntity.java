package com.casa.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "codigos")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodigoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false, referencedColumnName = "id")
	private RolEntity rol;
	
	@Column(nullable = false, length = 3, unique = true)
	private String prefijo;
	
	@Column(nullable = false, length = 3)
	private Integer consecutivo;
	
	@Column(length = 200)
	private String descripcion;
	
	@Column(length = 2)
	private Boolean eliminado;
	
	@Column(nullable = false, name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;

}
