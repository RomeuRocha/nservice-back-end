package com.unirios.gspi.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unirios.gspi.entidades.OrdemServico;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AgendaDTO {
	
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm",timezone = "GMT")
	private Instant startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm",timezone = "GMT")
	private Instant endDate;
	
	private String title;
	
	private String type;
	
	public AgendaDTO(OrdemServico os) {
		id = os.getId();
		startDate = os.getDateSchedule();
		endDate = os.getDateSchedule().plusSeconds(600L);
		title = os.getAssunto().getDescription();
		type = os.getSituation().getDescricao();
	}

}
