package com.unirios.gspi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.SubjectDTO;
import com.unirios.gspi.entities.Subject;
import com.unirios.gspi.repositories.SubjectRepository;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository repo;
	
	public List<Subject> findAll(){
		return repo.findAll();
	}
	
	public Subject findById(Long id) {
		Optional<Subject> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Subject.class.getName()));	
	}
	
	public Subject insert(Subject obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Subject update(Subject obj) {
		Subject newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public void delete(Long id) {
		findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Assunto que possui relação com Ordem de serviço");
		}
	}
	
	public Subject fromDto(SubjectDTO subjectDTO) {
		return new Subject(subjectDTO.getId(), subjectDTO.getDescription());
	}
	
	private void updateData(Subject newObj, Subject obj) {
		newObj.setDescription(obj.getDescription());
	}
	
}
