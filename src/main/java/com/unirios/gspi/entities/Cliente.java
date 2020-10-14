package com.unirios.gspi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor 
@EqualsAndHashCode(of = "id")
@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String whatsApp;
	
	private String telefone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OrderOfService> ordensDeServico = new ArrayList<OrderOfService>();

	public Cliente(Long id, String nome, String whatsApp, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.whatsApp = whatsApp;
		this.telefone = telefone;
	}
	
	

}
