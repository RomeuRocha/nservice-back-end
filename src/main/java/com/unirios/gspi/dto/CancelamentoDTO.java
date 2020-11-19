package com.unirios.gspi.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unirios.gspi.entidades.Cancelamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CancelamentoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String motivo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant data;

	public CancelamentoDTO(Cancelamento obj) {
		this.id = obj.getId();
		this.motivo = obj.getMotivo();
		this.data = obj.getData();
		
	}
	
	

}
