package com.unirios.gspi.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Servico")
public class Servico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	private String description;
	
	@Getter @Setter
	private Float value;
	
	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy = "service")
	private List<ItemService> servicesItens = new ArrayList<ItemService>();

	public Servico(Long id, String description, Float value) {
		super();
		this.id = id;
		this.description = description;
		this.value = value;
	}
	
	
	
}
