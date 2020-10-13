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

import com.unirios.gspi.dto.ResponsibleDTO;
import com.unirios.gspi.entities.Responsible;
import com.unirios.gspi.services.ResponsibleService;

@RestController
@RequestMapping(value = "/responsible")
public class ResponsibleResource {
	
	@Autowired
	private ResponsibleService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ResponsibleDTO>> findAll() {
		List<Responsible> list = service.findAll();
		List<ResponsibleDTO> listDto = list.stream().map(obj -> new ResponsibleDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ResponsibleDTO> findById(@PathVariable Long id) {
		Responsible obj = service.findById(id);
		ResponsibleDTO objDTO = new ResponsibleDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ResponsibleDTO objDto) {
		Responsible obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ResponsibleDTO objDto, @PathVariable Long id) {
		Responsible obj = service.fromDto(objDto);
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
