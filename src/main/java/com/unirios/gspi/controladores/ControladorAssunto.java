package com.unirios.gspi.controladores;

import java.net.URI;

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

import com.unirios.gspi.Servicos.ServicoAssunto;
import com.unirios.gspi.dto.AssuntoDTO;
import com.unirios.gspi.entidades.Assunto;

@RestController
@RequestMapping(value="/assunto")
public class ControladorAssunto {
	
	@Autowired
	private ServicoAssunto service;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<AssuntoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "field", defaultValue = "") String field) {
		Page<Assunto> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<AssuntoDTO> listDto = list.map(obj -> new AssuntoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AssuntoDTO> findById(@PathVariable Long id) {
		Assunto obj = service.findById(id);
		AssuntoDTO objDTO = new AssuntoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<AssuntoDTO> insert(@Valid @RequestBody AssuntoDTO objDto) {
		Assunto obj = service.fromDto(objDto);
		obj = service.insert(obj);
		AssuntoDTO serviDTO = new AssuntoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<AssuntoDTO> update(@Valid @RequestBody AssuntoDTO objDto, @PathVariable Long id) {
		Assunto obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		AssuntoDTO servDTO = new AssuntoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<AssuntoDTO> delete(@PathVariable Long id) {
		Assunto s = service.delete(id);
		AssuntoDTO servDTO = new AssuntoDTO(s);
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
	
	
	
	
	

}
