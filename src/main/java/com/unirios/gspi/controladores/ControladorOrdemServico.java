package com.unirios.gspi.controladores;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.unirios.gspi.dto.OrdemServicoDTO;
import com.unirios.gspi.entidades.OrdemServico;

@RestController
@RequestMapping(value = "/os")
public class ControladorOrdemServico {

	@Autowired
	private ServicoOrdemServico service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServico> list = service.findAll();
		List<OrdemServicoDTO> listDto = list.stream().map(obj -> new OrdemServicoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id) {
		OrdemServico obj = service.findById(id);
		OrdemServicoDTO objDTO = new OrdemServicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OrdemServicoDTO objDto) {
		OrdemServico obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody OrdemServicoDTO objDto, @PathVariable Long id) {
		OrdemServico obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<OrdemServicoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "saveMoment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<OrdemServico> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<OrdemServicoDTO> listDto = list.map(obj -> new OrdemServicoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
