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

import com.unirios.gspi.Servicos.ServicoServico;
import com.unirios.gspi.dto.FuncionarioDTO;
import com.unirios.gspi.dto.ServicoDTO;
import com.unirios.gspi.entidades.Funcionario;
import com.unirios.gspi.entidades.Servico;

@RestController
@RequestMapping(value="/servico")
public class ControladorServico {
	
	@Autowired
	private ServicoServico service;
	
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<ServicoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "field", defaultValue = "") String field) {
		Page<Servico> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<ServicoDTO> listDto = list.map(obj -> new ServicoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ServicoDTO> findById(@PathVariable Long id) {
		Servico obj = service.findById(id);
		ServicoDTO objDTO = new ServicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ServicoDTO> insert(@Valid @RequestBody ServicoDTO objDto) {
		Servico obj = service.fromDto(objDto);
		obj = service.insert(obj);
		ServicoDTO serviDTO = new ServicoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ServicoDTO> update(@Valid @RequestBody ServicoDTO objDto, @PathVariable Long id) {
		Servico obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		ServicoDTO servDTO = new ServicoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ServicoDTO> delete(@PathVariable Long id) {
		Servico s = service.delete(id);
		ServicoDTO servDTO = new ServicoDTO(s);
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
	public ResponseEntity<List<ServicoDTO>> findMany(@PathVariable Long[] ids) {
		List<ServicoDTO> list = new ArrayList<ServicoDTO>();
		
		for(Long id: ids) {
			Servico servico = service.findById(id);
			list.add(new ServicoDTO(servico));
		}
		
		return ResponseEntity.ok().body(list);
	}
	
	
	

}
