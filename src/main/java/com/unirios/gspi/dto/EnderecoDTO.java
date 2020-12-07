package com.unirios.gspi.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import com.unirios.gspi.entidades.Endereco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class EnderecoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String rua;
	
	private String numero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String bairro;
	
	private String cep;
	
	private String cidade;
	
	private String uf;


	public EnderecoDTO(Endereco obj) {
		this.id = obj.getId();
		this.rua = obj.getRua();
		this.numero = obj.getNumero();
		this.bairro = obj.getBairro();
		this.cep = obj.getCep();
		this.cidade = obj.getCidade();
		this.uf = obj.getUf();
	}
	
	
	
	
}
