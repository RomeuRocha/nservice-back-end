package com.unirios.gspi.controladores;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unirios.gspi.Servicos.ServicoAssunto;
import com.unirios.gspi.dto.AssuntoDTO;
import com.unirios.gspi.entidades.Assunto;

@RestController
@RequestMapping(value = "/assunto")
public class ControladorAssunto {

	@Autowired
	private ServicoAssunto service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AssuntoDTO>> findAll() {
		List<Assunto> list = service.findAll();
		List<AssuntoDTO> listDto = list.stream().map(obj -> new AssuntoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AssuntoDTO> findById(@PathVariable Long id) {
		Assunto obj = service.findById(id);
		AssuntoDTO objDTO = new AssuntoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AssuntoDTO objDto) {
		Assunto obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AssuntoDTO objDto, @PathVariable Long id) {
		Assunto obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
