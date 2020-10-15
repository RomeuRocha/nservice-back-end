package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.unirios.gspi.dto.ServicoDTO;
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
	
	public void delete(Long id) {
		findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
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
	
}
