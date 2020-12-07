package com.unirios.gspi.controladores;

import java.net.URI;
import java.util.ArrayList;
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

import com.unirios.gspi.Servicos.ServicoEndereco;
import com.unirios.gspi.dto.EnderecoDTO;
import com.unirios.gspi.entidades.Endereco;

@RestController
@RequestMapping(value="/endereco")
public class ControladorEndereco {
	
	@Autowired
	private ServicoEndereco service;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<EnderecoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "field", defaultValue = "") String field) {
		Page<Endereco> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<EnderecoDTO> listDto = list.map(obj -> new EnderecoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
		Endereco obj = service.findById(id);
		EnderecoDTO objDTO = new EnderecoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<EnderecoDTO> insert(@Valid @RequestBody EnderecoDTO objDto) {
		Endereco obj = service.fromDto(objDto);
		obj = service.insert(obj);
		EnderecoDTO serviDTO = new EnderecoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<EnderecoDTO> update(@Valid @RequestBody EnderecoDTO objDto, @PathVariable Long id) {
		Endereco obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		EnderecoDTO servDTO = new EnderecoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<EnderecoDTO> delete(@PathVariable Long id) {
		Endereco s = service.delete(id);
		EnderecoDTO servDTO = new EnderecoDTO(s);
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
	
	@RequestMapping(value="/many/{ids}", method=RequestMethod.GET)
	public ResponseEntity<List<EnderecoDTO>> findMany(@PathVariable Long[] ids) {
		List<EnderecoDTO> list = new ArrayList<EnderecoDTO>();
		
		for(Long id: ids) {
			Endereco endereco = service.findById(id);
			list.add(new EnderecoDTO(endereco));
		}
		
		return ResponseEntity.ok().body(list);
	}
	
	
}
