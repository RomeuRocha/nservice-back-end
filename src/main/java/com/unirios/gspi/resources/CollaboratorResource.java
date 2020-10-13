package com.unirios.gspi.resources;

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

import com.unirios.gspi.dto.CollaboratorDTO;
import com.unirios.gspi.entities.Collaborator;
import com.unirios.gspi.services.CollaboratorService;

@RestController
@RequestMapping(value = "/responsible")
public class CollaboratorResource {
	
	@Autowired
	private CollaboratorService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CollaboratorDTO>> findAll() {
		List<Collaborator> list = service.findAll();
		List<CollaboratorDTO> listDto = list.stream().map(obj -> new CollaboratorDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CollaboratorDTO> findById(@PathVariable Long id) {
		Collaborator obj = service.findById(id);
		CollaboratorDTO objDTO = new CollaboratorDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CollaboratorDTO objDto) {
		Collaborator obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CollaboratorDTO objDto, @PathVariable Long id) {
		Collaborator obj = service.fromDto(objDto);
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
