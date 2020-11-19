package com.unirios.gspi.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
public class Cancelamento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant data;
	
	@Getter @Setter
	private String motivo;

	public Cancelamento(Long id, Instant data, String motivo) {
		super();
		this.id = id;
		this.data = data;
		this.motivo = motivo;
	}
	
	
}
