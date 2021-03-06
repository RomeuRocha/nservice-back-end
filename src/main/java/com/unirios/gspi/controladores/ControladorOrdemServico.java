package com.unirios.gspi.controladores;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unirios.gspi.Servicos.ServicoOrdemServico;
import com.unirios.gspi.dto.Grafico1DTO;
import com.unirios.gspi.dto.OrdemServicoDTO;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.entities.Enuns.Status;

@RestController
@RequestMapping(value="/ordemservico")
public class ControladorOrdemServico {
	
	@Autowired
	private ServicoOrdemServico service;
	
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<OrdemServicoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "textinput", defaultValue = "") String field,
			@RequestParam(value = "assunto", defaultValue = "") String assunto,
			@RequestParam(value = "situacao", defaultValue = "0") Integer situacao,
			@RequestParam(value = "dataInicial", defaultValue = "") String dataInicial,
			@RequestParam(value = "dataFinal", defaultValue = "") String dataFinal) {
		
		Page<OrdemServico> list = service.findPage(page, linesPerPage, orderBy, direction,field,assunto,situacao,dataInicial,dataFinal);
		Page<OrdemServicoDTO> listDto = list.map(obj -> new OrdemServicoDTO(obj));  
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id) {
		OrdemServico obj = service.findById(id);
		OrdemServicoDTO objDTO = new OrdemServicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<OrdemServicoDTO> insert(@Valid @RequestBody OrdemServicoDTO objDto) {
		objDto.setSaveMoment(Instant.now());
		objDto.setSituation(Status.ANALISE);
		OrdemServico obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		OrdemServicoDTO serviDTO = new OrdemServicoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<OrdemServicoDTO> update(@Valid @RequestBody OrdemServicoDTO objDto, @PathVariable Long id) {
		System.out.println("===========================");
		System.out.println(objDto.getSituation());
		OrdemServico obj = service.fromDTO(objDto);
		obj.setId(id);
		
		obj = service.update(obj);
		OrdemServicoDTO servDTO = new OrdemServicoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<OrdemServicoDTO> delete(@PathVariable Long id) {
		OrdemServico s = service.delete(id);
		OrdemServicoDTO servDTO = new OrdemServicoDTO(s);
		return ResponseEntity.ok().body(servDTO);
	}
	
	@RequestMapping(value="/many/{ids}", method=RequestMethod.DELETE)
	public ResponseEntity<Long[]> delete(@PathVariable Long[] ids) {
		
		for(Long id: ids) {
			service.delete(id);
		}
		Long[] vars = ids;
		return ResponseEntity.ok().body(vars);
	}
	
	@RequestMapping(value="/grafico1", method=RequestMethod.GET)
	public ResponseEntity<List<Grafico1DTO>> findGrafico() {
		List<Grafico1DTO> g1 = service.findGrafico1(3);
		
		return ResponseEntity.ok().body(g1);
		
	}
	
	@RequestMapping(value="/abertas", method=RequestMethod.GET)
	public ResponseEntity<Integer> findOsAbertas() {
		List<OrdemServico> g1 = service.findAbertas(2);
		Integer total = g1.size();
		return ResponseEntity.ok().body(total);
		
	}
	
	
	

}
