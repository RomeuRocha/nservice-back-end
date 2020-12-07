package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.unirios.gspi.dto.ServicoDTO;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.entidades.Servico;
import com.unirios.gspi.repositorios.RepositorioServico;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@org.springframework.stereotype.Service
public class ServicoServico {

	
	@Autowired
	private RepositorioServico repo;
	
	public List<Servico> findAll(){
		return repo.findAll();
	}
	
	public Servico findById(Long id) {
		Optional<Servico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName()));	
	}
	
	public Servico insert(Servico obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Servico update(Servico obj) {
		Servico newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public Servico delete(Long id) {
		Servico s = findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return s;
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um serviço que possui relação com Ordem de serviço");
		}
		
	}
	
	public Servico fromDto(ServicoDTO serviDTO) {
		return new Servico(serviDTO.getId(), serviDTO.getDescription(), serviDTO.getValue());
	}
	
	private void updateData(Servico newObj, Servico obj) {
		newObj.setDescription(obj.getDescription());
		newObj.setValue(obj.getValue());
	}
	
	public Page<Servico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarServicosPaginados(field.toLowerCase(),pageRequest);
	}
	
}
