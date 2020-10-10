package com.unirios.gspi.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
