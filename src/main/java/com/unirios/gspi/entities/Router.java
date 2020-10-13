package com.unirios.gspi.entities;

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
@Table(name = "Roteador")
public class Router implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String mac;
	@Getter @Setter
	private String ip;
	@Getter @Setter
	private String mascara;
	@Getter @Setter
	private String usuario;
	@Getter @Setter
	private String senha;
	
	
	
}
