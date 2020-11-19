package com.unirios.gspi.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unirios.gspi.entidades.Adesao;
import com.unirios.gspi.entidades.Cancelamento;
import com.unirios.gspi.entidades.Cliente;
import com.unirios.gspi.entidades.Endereco;
import com.unirios.gspi.entidades.Plano;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AdesaoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant data;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private double valor;
	

	private Plano plano;
	

	private Cancelamento cancelamento;
	

	private Endereco endereco;
	
	private Cliente cliente;

	public AdesaoDTO(Adesao obj) {
		id = obj.getId();
		data = obj.getData();
		valor = obj.getValor();
		plano = obj.getPlano();
		cancelamento = obj.getCancelamento();
		endereco = obj.getEndereco();
		cliente = obj.getCliente();
	}

}
