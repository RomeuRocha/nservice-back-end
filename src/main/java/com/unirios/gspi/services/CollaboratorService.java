package com.unirios.gspi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.CollaboratorDTO;
import com.unirios.gspi.entities.Collaborator;
import com.unirios.gspi.repositories.CollaboratorRepository;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class CollaboratorService {
	

	@Autowired
	private CollaboratorRepository repo;
	
	public List<Collaborator> findAll(){
		return repo.findAll();
	}
	
	public Collaborator findById(Long id) {
		Optional<Collaborator> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Collaborator.class.getName()));	
	}
	
	public Collaborator insert(Collaborator obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Collaborator update(Collaborator obj) {
		Collaborator newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public void delete(Long id) {
		findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um funcionário que possui relação com Ordem de serviço");
		}
	}
	
	public Collaborator fromDto(CollaboratorDTO responsibleDTO) {
		return new Collaborator(responsibleDTO.getId(), responsibleDTO.getName(), responsibleDTO.getEmail(), responsibleDTO.getWhatsApp(), responsibleDTO.getCargo(), responsibleDTO.getDepartamento(), responsibleDTO.getLogin(), responsibleDTO.getSenha());
	}
	
	private void updateData(Collaborator newObj, Collaborator obj) {
		newObj.setName(obj.getName());
		newObj.setCargo(obj.getCargo());
		newObj.setDepartamento(obj.getDepartamento());
		newObj.setEmail(obj.getEmail());
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
		newObj.setWhatsApp(obj.getWhatsApp());
	}
	
}
