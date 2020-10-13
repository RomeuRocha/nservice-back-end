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
@Table(name = "Plano")
public class Plano implements Serializable{
	
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
	private Float valueMother;
	
	@Getter @Setter
	private String name;
	
	
}
