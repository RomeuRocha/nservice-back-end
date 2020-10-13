package com.unirios.gspi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.unirios.gspi.entities.Collaborator;
import com.unirios.gspi.entities.Enuns.Departamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter


public class CollaboratorDTO {

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;

	@NotEmpty(message="Preenchimento obrigatório")
	private String whatsApp;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cargo;
	

	private Integer departamento;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 20 caracteres")
	private String login;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 20 caracteres")
	private String senha;
	
	public CollaboratorDTO(Collaborator respon) {
		id = respon.getId();
		name = respon.getName();
		email = respon.getEmail();
		whatsApp = respon.getWhatsApp();
		cargo = respon.getCargo();
		departamento= respon.getDepartamento().getCod();
		login = respon.getLogin();
		senha = respon.getSenha();
	}
	
	public Departamento getDepartamento() {
		return Departamento.toEnum(departamento);
	}
	
	public void setDepartamento(Departamento dep) {
		departamento = dep.getCod();
	}

}
