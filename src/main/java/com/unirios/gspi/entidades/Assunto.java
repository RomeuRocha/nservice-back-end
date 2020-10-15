package com.unirios.gspi.entidades;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "Assunto")
public class Assunto implements Serializable{
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "subject")
	private List<OrdemServico> orderServices = new ArrayList<OrdemServico>();

	public Assunto(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	

}
