package com.unirios.gspi.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor 
@EqualsAndHashCode(of = "id")
@Getter
@Setter

@Entity
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String whatsApp;
	
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> ordensDeServico = new ArrayList<OrdemServico>();

	public Cliente(Long id, String nome, String whatsApp,String email, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.whatsApp = whatsApp;
		this.cpf = cpf;
		this.email = email;
	}
	
	

}
