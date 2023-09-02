package com.casa.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notas")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
	private Long id;
	
	@Column(nullable = false, length = 10)
	private Float nota;
	
	@ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false, referencedColumnName = "id")
    private UsuarioEntity estudiante;
	
	@ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false, referencedColumnName = "id")
    private UsuarioEntity profesor;
	
	@Column(nullable = false, name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;
		
}
