package com.unirios.gspi.dto;

import com.unirios.gspi.entities.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter 
@Setter
@NoArgsConstructor


public class ServiceDTO {
	
	private Long id;
	
	private String description;
	
	private Float value;
	
	public ServiceDTO(Service s) {
		id = s.getId();
		description = s.getDescription();
		value = s.getValue();
	}
}
