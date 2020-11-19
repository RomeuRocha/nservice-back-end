package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.CancelamentoDTO;
import com.unirios.gspi.entidades.Cancelamento;
import com.unirios.gspi.repositorios.RepositorioCancelamento;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoCancelamento {

	@Autowired
	private RepositorioCancelamento repo;
	
	public List<Cancelamento> findAll(){
		return repo.findAll();
	}
	
	public Cancelamento findById(Long id) {
		Optional<Cancelamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cancelamento.class.getName()));	
	}
	
	public Cancelamento insert(Cancelamento obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Cancelamento update(Cancelamento obj) {
		Cancelamento newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public Cancelamento delete(Long id) {
		Cancelamento a = findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return a;
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cancelamento que possui relação com Ordem de serviço");
		}
	}
	
	public Cancelamento fromDto(CancelamentoDTO subjectDTO) {
		return new Cancelamento(subjectDTO.getId(), subjectDTO.getData(),subjectDTO.getMotivo());
	}
	
	private void updateData(Cancelamento newObj, Cancelamento obj) {
		newObj.setData(obj.getData());
		newObj.setMotivo(obj.getMotivo());
		
	}
	
	public Page<Cancelamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarServicosPaginados(field.toLowerCase(),pageRequest);
		
	}
	
}
