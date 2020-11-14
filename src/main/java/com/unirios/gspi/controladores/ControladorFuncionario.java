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

import com.unirios.gspi.Servicos.ServicoFuncionario;
import com.unirios.gspi.dto.FuncionarioDTO;
import com.unirios.gspi.entidades.Funcionario;

@RestController
@RequestMapping(value="/funcionario")
public class ControladorFuncionario {
	
	@Autowired
	private ServicoFuncionario service;
	
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<FuncionarioDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "field", defaultValue = "") String field) {
		Page<Funcionario> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<FuncionarioDTO> listDto = list.map(obj -> new FuncionarioDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
		Funcionario obj = service.findById(id);
		FuncionarioDTO objDTO = new FuncionarioDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<FuncionarioDTO> insert(@Valid @RequestBody FuncionarioDTO objDto) {
		Funcionario obj = service.fromDto(objDto);
		obj = service.insert(obj);
		FuncionarioDTO serviDTO = new FuncionarioDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<FuncionarioDTO> update(@Valid @RequestBody FuncionarioDTO objDto, @PathVariable Long id) {
		Funcionario obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		FuncionarioDTO servDTO = new FuncionarioDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<FuncionarioDTO> delete(@PathVariable Long id) {
		Funcionario s = service.delete(id);
		FuncionarioDTO servDTO = new FuncionarioDTO(s);
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
