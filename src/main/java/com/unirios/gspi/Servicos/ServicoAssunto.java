package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.AssuntoDTO;
import com.unirios.gspi.entidades.Assunto;
import com.unirios.gspi.repositorios.RepositorioAssunto;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoAssunto {

	@Autowired
	private RepositorioAssunto repo;
	
	public List<Assunto> findAll(){
		return repo.findAll();
	}
	
	public Assunto findById(Long id) {
		Optional<Assunto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Assunto.class.getName()));	
	}
	
	public Assunto insert(Assunto obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Assunto update(Assunto obj) {
		Assunto newObj = findById(obj.getId());
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
	
	public Assunto fromDto(AssuntoDTO subjectDTO) {
		return new Assunto(subjectDTO.getId(), subjectDTO.getDescription());
	}
	
	private void updateData(Assunto newObj, Assunto obj) {
		newObj.setDescription(obj.getDescription());
	}
	
}
