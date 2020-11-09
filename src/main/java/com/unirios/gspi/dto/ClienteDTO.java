package com.unirios.gspi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.unirios.gspi.entidades.Cliente;
import com.unirios.gspi.entidades.OrdemServico;

import lombok.Data;

@Data
public class ClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String whatsApp;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF
	private String cpf;

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.whatsApp = cliente.getWhatsApp();
		this.cpf = cliente.getCpf();
	}
	
	

}
