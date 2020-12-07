package com.unirios.gspi.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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

import com.unirios.gspi.Servicos.ServicoAdesao;
import com.unirios.gspi.Servicos.ServicoEndereco;
import com.unirios.gspi.dto.AdesaoDTO;
import com.unirios.gspi.dto.Grafico2DTO;
import com.unirios.gspi.entidades.Adesao;
import com.unirios.gspi.entidades.Endereco;

@RestController
@RequestMapping(value="/adesao")
public class ControladorAdesao {
	
	@Autowired
	private ServicoAdesao service;
	
	@Autowired
	private ServicoEndereco serviceEndereco;

	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<AdesaoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "textinput", defaultValue = "") String field) {
		Page<Adesao> list = service.findPage(page, linesPerPage, orderBy, direction,field);
		Page<AdesaoDTO> listDto = list.map(obj -> new AdesaoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AdesaoDTO> findById(@PathVariable Long id) {
		Adesao obj = service.findById(id);
		AdesaoDTO objDTO = new AdesaoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<AdesaoDTO> insert(@Valid @RequestBody AdesaoDTO objDto) {
		objDto.setData(new Date());
		Adesao obj = service.fromDto(objDto);
		Endereco end = serviceEndereco.insert(obj.getEndereco());
		obj = service.insert(obj);
		AdesaoDTO serviDTO = new AdesaoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(serviDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<AdesaoDTO> update(@Valid @RequestBody AdesaoDTO objDto, @PathVariable Long id) {
		Adesao obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		AdesaoDTO servDTO = new AdesaoDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(servDTO);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<AdesaoDTO> delete(@PathVariable Long id) {
		Adesao s = service.delete(id);
		AdesaoDTO servDTO = new AdesaoDTO(s);
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
	public ResponseEntity<List<AdesaoDTO>> findMany(@PathVariable Long[] ids) {
		List<AdesaoDTO> list = new ArrayList<AdesaoDTO>();
		
		for(Long id: ids) {
			Adesao adesao = service.findById(id);
			list.add(new AdesaoDTO(adesao));
		}
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/ativas", method=RequestMethod.GET)
	public ResponseEntity<Long> findActive() {
		Long s = service.findActive();
		return ResponseEntity.ok().body(s);
	}
	
	@RequestMapping(value="/grafico2", method=RequestMethod.GET)
	public ResponseEntity<List<Grafico2DTO>> findGrafico() {
		List<Grafico2DTO> g1 = service.findGrafico2();
		
		return ResponseEntity.ok().body(g1);
		
	}

}
