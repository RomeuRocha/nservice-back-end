package com.unirios.gspi.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unirios.gspi.Servicos.ServicoAdesao;
import com.unirios.gspi.entidades.Adesao;
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
		
		startDate = os.getDateSchedule();
		endDate = os.getDateSchedule().plusSeconds(7200L);
		title = os.getAssunto().getDescription()+" - "+os.getCliente().getNome() ;
		type = os.getSituation().getDescricao();
	}

}
