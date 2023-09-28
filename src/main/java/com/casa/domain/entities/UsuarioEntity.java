package com.casa.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
	private Long id;
	
	@Column(nullable = false, length = 6, unique = true)
	private String codigo;
	
	@Column(name = "tipo_usuario", nullable = false, length = 11)
	private String tipoUsuario;
	
	@Column(name = "tipo_documento", nullable = false, length = 10)
	private String tipoDocumento;
	
	@Column(name = "numero_documento", nullable = false, length = 100, unique = true)
	private String numeroDocumento;
	
	@Column(length = 100)
	private String nombres;
	
	@Column(length = 100, nullable = false)
	private String apellidos;
	
	@Column(nullable = false, length = 20)
	private String celular;

	@Column(length = 200)
	private String direccion;

	@Column(length = 2)
	private Integer edad;

	@Column(nullable = false, length = 150, unique = true)
	private String email;
	
	@Column(nullable = false, length = 20, unique = true)
	private String usuario;
	
	@Column(nullable = false, length = 200)
	private String contrasenna;
	
	@Column(nullable = false)
	private Boolean eliminado;
		
	@Column(nullable = false, name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;

}
