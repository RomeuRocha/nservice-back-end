package com.unirios.gspi.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Grafico2DTO {

	private Integer LineValue;
	private Integer argument;
	private String mes;
	
	public Grafico2DTO(Integer valor, Integer mes, String mesS) {
		this.LineValue = valor;
		this.argument = mes;
		this.mes = mesS;
	}
	
	
}
