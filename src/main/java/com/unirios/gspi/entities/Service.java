package com.unirios.gspi.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Servico")
public class Service implements Serializable{
	
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
	
	@Getter @Setter
	@OneToMany(mappedBy = "id.service")
	private Set<ItemService> servicesItens = new HashSet<ItemService>();

	public Service(Long id, String description, Float value) {
		super();
		this.id = id;
		this.description = description;
		this.value = value;
	}
	
	
	
}
