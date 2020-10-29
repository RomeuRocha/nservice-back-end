package com.unirios.gspi.entidades;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter


@Entity
@JsonTypeName("clienteFisico")
public class ClienteFisico extends Cliente{
	
	private static final long serialVersionUID = 1L;
	
	@CPF
	private String cpf;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;

	public ClienteFisico(Long id, String nome, String whatsApp, String telefone, String cpf, Date dataNascimento) {
		super(id, nome, whatsApp, telefone);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
