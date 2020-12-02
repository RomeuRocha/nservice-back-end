package com.unirios.gspi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.unirios.gspi.Servicos.ServicoOrdemServico;
import com.unirios.gspi.dto.AgendaDTO;
import com.unirios.gspi.entidades.OrdemServico;

@RestController
@RequestMapping(value="/agenda")
public class ControladorAgenda {
	
	@Autowired
	private ServicoOrdemServico service;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<AgendaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "field", defaultValue = "") String field) {
		Page<OrdemServico> list = service.findPageAgenda(page, linesPerPage, orderBy, direction,field);
		Page<AgendaDTO> listDto = list.map(obj -> new AgendaDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
}
