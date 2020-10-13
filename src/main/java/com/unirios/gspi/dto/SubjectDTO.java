package com.unirios.gspi.dto;

import javax.validation.constraints.NotEmpty;

import com.unirios.gspi.entities.Subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SubjectDTO {

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String description;
	
	public SubjectDTO(Subject obj) {
		id = obj.getId();
		description = obj.getDescription();
	}

}
