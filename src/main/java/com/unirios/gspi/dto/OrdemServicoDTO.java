package com.unirios.gspi.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unirios.gspi.entidades.Assunto;
import com.unirios.gspi.entidades.Cliente;
import com.unirios.gspi.entidades.Funcionario;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.entities.Enuns.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class OrdemServicoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Funcionario funcionario;
	
	private Cliente cliente;
	
	private Assunto assunto;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant saveMoment;//momento salvo;//deverá ser feito em trigger
	
	//"yyy-MM-dd'T'HH:mm:ss.SSS'Z'"
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm",timezone = "GMT")
	private Instant dateSchedule;//data e hora do agendamento

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant attendance;//data e hora do atendimento
	
	private Status situation;
	
	private List<ServicoDTO> servicos = new ArrayList<>();
	

	public OrdemServicoDTO(OrdemServico os) {
		this.id = os.getId();
		this.funcionario = os.getCollaborator();
		this.cliente = os.getCliente();
		this.assunto = os.getAssunto();
		this.saveMoment = os.getSaveMoment();
		this.dateSchedule = os.getDateSchedule();
		this.attendance = os.getAttendance();
		this.situation = os.getSituation();
		servicos = os.getServicesItens().stream().map(x -> new ServicoDTO(x.getService())).collect(Collectors.toList());
		
	}
	
	

}
