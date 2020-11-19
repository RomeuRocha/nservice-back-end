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

import com.unirios.gspi.Servicos.ServicoCancelamento;
import com.unirios.gspi.dto.CancelamentoDTO;
import com.unirios.gspi.entidades.Cancelamento;

@RestController
@RequestMapping(value="/cancelamento")
public class ControladorCancelamento {
	
	@Autowired
	private ServicoCancelamento service;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<CancelamentoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "field", defaultValue = "") String field) {
		Page<Cancelamento> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<CancelamentoDTO> listDto = list.map(obj -> new CancelamentoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CancelamentoDTO> findById(@PathVariable Long id) {
		Cancelamento obj = service.findById(id);
		CancelamentoDTO objDTO = new CancelamentoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CancelamentoDTO> insert(@Valid @RequestBody CancelamentoDTO objDto) {
		Cancelamento obj = service.fromDto(objDto);
		obj = service.insert(obj);
		CancelamentoDTO serviDTO = new CancelamentoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<CancelamentoDTO> update(@Valid @RequestBody CancelamentoDTO objDto, @PathVariable Long id) {
		Cancelamento obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		CancelamentoDTO servDTO = new CancelamentoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CancelamentoDTO> delete(@PathVariable Long id) {
		Cancelamento s = service.delete(id);
		CancelamentoDTO servDTO = new CancelamentoDTO(s);
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
