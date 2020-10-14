package com.unirios.gspi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.unirios.gspi.entities.Enuns.Estado;

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
	private String street;
	
	@Getter @Setter
	private String number;
	
	@Getter @Setter
	private String bairro;

	@Getter @Setter
	private String cep;
	
	@Getter @Setter
	@ManyToOne
	private Cidade cidade;
	
}
