package com.unirios.gspi.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Endereço")
public class Endereco implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Getter @Setter
	private String rua;
	
	@Getter @Setter
	private String numero;
	
	@Getter @Setter
	private String bairro;

	@Getter @Setter
	private String cep;
	
	@Getter @Setter
	private String cidade;
	
	@Getter @Setter
	private String uf;
	
}
