package com.unirios.gspi.dto;

import java.io.Serializable;
import java.util.Date;

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
	
	private Date data;
	
	
	private double valor;
	

	private Plano plano;
	

	private Cancelamento cancelamento;
	

	private Endereco endereco;
	
	private Cliente cliente;

	public AdesaoDTO(Adesao obj) {
		id = obj.getId();
		data = Date.from(obj.getData());
		valor = obj.getValor();
		plano = obj.getPlano();
		cancelamento = obj.getCancelamento();
		endereco = obj.getEndereco();
		cliente = obj.getCliente();
	}

}
