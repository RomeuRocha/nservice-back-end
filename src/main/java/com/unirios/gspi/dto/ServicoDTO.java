package com.unirios.gspi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.unirios.gspi.entidades.Servico;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter 
@Setter
@NoArgsConstructor


public class ServicoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	private String description;
	@NotNull(message = "Preenchimento obrigatório")
	private Float value;
	
	public ServicoDTO(Servico s) {
		id = s.getId();
		description = s.getDescription();
		value = s.getValue();
	}
}
