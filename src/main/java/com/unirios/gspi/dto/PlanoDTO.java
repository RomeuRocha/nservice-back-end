package com.unirios.gspi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.unirios.gspi.entidades.Plano;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PlanoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String descricao;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Float valorMensal;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;

	public PlanoDTO(Plano obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.valorMensal = obj.getValorMensal();
		this.nome = obj.getNome();
	}
	
	

}
