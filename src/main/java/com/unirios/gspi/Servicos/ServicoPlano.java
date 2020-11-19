package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.PlanoDTO;
import com.unirios.gspi.entidades.Plano;
import com.unirios.gspi.repositorios.RepositorioPlano;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoPlano {

	@Autowired
	private RepositorioPlano repo;
	
	public List<Plano> findAll(){
		return repo.findAll();
	}
	
	public Plano findById(Long id) {
		Optional<Plano> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Plano.class.getName()));	
	}
	
	public Plano insert(Plano obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Plano update(Plano obj) {
		Plano newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public Plano delete(Long id) {
		Plano a = findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return a;
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Plano que possui relação com Ordem de serviço");
		}
	}
	
	public Plano fromDto(PlanoDTO subjectDTO) {
		return new Plano(subjectDTO.getId(), subjectDTO.getDescricao(), subjectDTO.getValorMensal(), subjectDTO.getNome());
	}
	
	private void updateData(Plano newObj, Plano obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setValorMensal(obj.getValorMensal());
		newObj.setNome(obj.getNome());
	}
	
	public Page<Plano> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarServicosPaginados(field.toLowerCase(),pageRequest);
		//return repo.findAll(pageRequest);

	}
	
}
