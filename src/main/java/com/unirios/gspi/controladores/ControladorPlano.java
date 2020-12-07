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

import com.unirios.gspi.Servicos.ServicoPlano;
import com.unirios.gspi.Servicos.ServicoPlano;
import com.unirios.gspi.dto.AdesaoDTO;
import com.unirios.gspi.dto.PlanoDTO;
import com.unirios.gspi.entidades.Adesao;
import com.unirios.gspi.entidades.Plano;
import com.unirios.gspi.entidades.Plano;

@RestController
@RequestMapping(value="/plano")
public class ControladorPlano {

	@Autowired
	private ServicoPlano service;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<PlanoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "textinput", defaultValue = "") String field) {
		Page<Plano> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<PlanoDTO> listDto = list.map(obj -> new PlanoDTO(obj));  
		return ResponseEntity.ok().body(listDto); 
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PlanoDTO> findById(@PathVariable Long id) {
		Plano obj = service.findById(id);
		PlanoDTO objDTO = new PlanoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PlanoDTO> insert(@Valid @RequestBody PlanoDTO objDto) {
		Plano obj = service.fromDto(objDto);
		obj = service.insert(obj);
		PlanoDTO serviDTO = new PlanoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<PlanoDTO> update(@Valid @RequestBody PlanoDTO objDto, @PathVariable Long id) {
		Plano obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		PlanoDTO servDTO = new PlanoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<PlanoDTO> delete(@PathVariable Long id) {
		Plano s = service.delete(id);
		PlanoDTO servDTO = new PlanoDTO(s);
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
	public ResponseEntity<List<PlanoDTO>> findMany(@PathVariable Long[] ids) {
		List<PlanoDTO> list = new ArrayList<PlanoDTO>();
		
		for(Long id: ids) {
			Plano plano = service.findById(id);
			list.add(new PlanoDTO(plano));
		}
		
		return ResponseEntity.ok().body(list);
	}
	
}
