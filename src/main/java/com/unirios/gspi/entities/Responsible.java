package com.unirios.gspi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.unirios.gspi.entities.Enuns.Departamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Funcionario")
public class Responsible implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String whatsApp;
	
	@Getter @Setter
	private String cargo;
	
	private Integer departamento;//getter e setter deve ser personalizado para enum
	
	@Getter @Setter
	private String login;
	
	@Getter @Setter
	private String senha;

	public Responsible(Long id, String name, String email, String whatsApp, String cargo, Departamento departamento,
			String login, String senha) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.whatsApp = whatsApp;
		this.cargo = cargo;
		this.departamento = departamento.getCod();
		this.login = login;
		this.senha = senha;
	}
	
	public Departamento getDepartamento() {
		return Departamento.toEnum(departamento);
	}
	
	public void setDepartamento(Departamento dep) {
		departamento = dep.getCod();
	}
	
}
