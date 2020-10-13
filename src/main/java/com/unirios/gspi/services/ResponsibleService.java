package com.unirios.gspi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.ResponsibleDTO;
import com.unirios.gspi.entities.Responsible;
import com.unirios.gspi.repositories.ResponsibleRepository;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ResponsibleService {
	

	@Autowired
	private ResponsibleRepository repo;
	
	public List<Responsible> findAll(){
		return repo.findAll();
	}
	
	public Responsible findById(Long id) {
		Optional<Responsible> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Responsible.class.getName()));	
	}
	
	public Responsible insert(Responsible obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Responsible update(Responsible obj) {
		Responsible newObj = findById(obj.getId());
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
	
	public Responsible fromDto(ResponsibleDTO responsibleDTO) {
		return new Responsible(responsibleDTO.getId(), responsibleDTO.getName(), responsibleDTO.getEmail(), responsibleDTO.getWhatsApp(), responsibleDTO.getCargo(), responsibleDTO.getDepartamento(), responsibleDTO.getLogin(), responsibleDTO.getSenha());
	}
	
	private void updateData(Responsible newObj, Responsible obj) {
		newObj.setName(obj.getName());
		newObj.setCargo(obj.getCargo());
		newObj.setDepartamento(obj.getDepartamento());
		newObj.setEmail(obj.getEmail());
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
		newObj.setWhatsApp(obj.getWhatsApp());
	}
	
}
