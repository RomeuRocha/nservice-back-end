package com.unirios.gspi.entidades;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@JsonTypeName("clienteJuridico")
public class ClienteJuridico extends Cliente{
	
	private static final long serialVersionUID = 1L;
	
	private String cnpj;
	
	private String razaoSocial;
	
	private String inscricaoMunicipal;
	
	private String inscricaoEstadual;

	public ClienteJuridico(Long id, String nome, String whatsApp, String telefone, String cnpj, String razaoSocial,
			String inscricaoMunicipal, String inscricaoEstadual) {
		super(id, nome, whatsApp, telefone);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.inscricaoEstadual = inscricaoEstadual;
	}
		
}
