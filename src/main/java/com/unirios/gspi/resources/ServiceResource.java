package com.unirios.gspi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unirios.gspi.dto.ServiceDTO;
import com.unirios.gspi.entities.Service;
import com.unirios.gspi.services.ServiceService;

@RestController
@RequestMapping(value="/service")
public class ServiceResource {
	
	@Autowired
	ServiceService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ServiceDTO>> findAll() {
		List<Service> list = service.findAll();
		List<ServiceDTO> listDto = list.stream().map(obj -> new ServiceDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ServiceDTO> findById(@PathVariable Long id) {
		Service obj = service.findById(id);
		ServiceDTO objDTO = new ServiceDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ServiceDTO objDto) {
		Service obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
