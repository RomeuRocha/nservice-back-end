package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.EnderecoDTO;
import com.unirios.gspi.entidades.Endereco;
import com.unirios.gspi.repositorios.RepositorioEndereco;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoEndereco {

	@Autowired
	private RepositorioEndereco repo;
	
	public List<Endereco> findAll(){
		return repo.findAll();
	}
	
	public Endereco findById(Long id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));	
	}
	
	public Endereco insert(Endereco obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Endereco update(Endereco obj) {
		Endereco newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public Endereco delete(Long id) {
		Endereco a = findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return a;
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Endereco que possui relação com Ordem de serviço");
		}
	}
	
	public Endereco fromDto(EnderecoDTO subjectDTO) {
		return new Endereco(subjectDTO.getId(), subjectDTO.getRua(), subjectDTO.getNumero(), 
				subjectDTO.getBairro(),subjectDTO.getCep(),subjectDTO.getCidade(),subjectDTO.getUf());
	}
	
	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setRua(obj.getRua());
		newObj.setNumero(obj.getNumero());
		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
		newObj.setCidade(obj.getCidade());
	}
	
	public Page<Endereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarServicosPaginados(field.toLowerCase(),pageRequest);
		//return repo.findAll(pageRequest);

	}
	
}
