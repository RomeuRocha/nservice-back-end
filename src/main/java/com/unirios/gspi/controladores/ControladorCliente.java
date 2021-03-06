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

import com.unirios.gspi.Servicos.ServicoCliente;
import com.unirios.gspi.dto.ClienteDTO;
import com.unirios.gspi.entidades.Cliente;

@RestController
@RequestMapping(value="/cliente")
public class ControladorCliente {
	
	@Autowired
	private ServicoCliente service;
	
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "textinput", defaultValue = "") String field) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		Cliente obj = service.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ClienteDTO> insert(@Valid @RequestBody ClienteDTO objDto) {
		Cliente obj = service.fromDto(objDto);
		obj = service.insert(obj);
		ClienteDTO serviDTO = new ClienteDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Long id) {
		Cliente obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		ClienteDTO servDTO = new ClienteDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ClienteDTO> delete(@PathVariable Long id) {
		Cliente s = service.delete(id);
		ClienteDTO servDTO = new ClienteDTO(s);
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
	public ResponseEntity<List<ClienteDTO>> findMany(@PathVariable Long[] ids) {
		List<ClienteDTO> list = new ArrayList<ClienteDTO>();
		
		for(Long id: ids) {
			Cliente cliente = service.findById(id);
			list.add(new ClienteDTO(cliente));
		}
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/totalClientes", method=RequestMethod.GET)
	public ResponseEntity<Long> findTotalCliente() {
		List<Cliente> list = service.findAll();
		Long total = (long) list.size(); 
				
		return ResponseEntity.ok().body(total);
	}
	

}
