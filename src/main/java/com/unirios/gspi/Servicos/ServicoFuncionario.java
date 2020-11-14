package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.FuncionarioDTO;
import com.unirios.gspi.entidades.Funcionario;
import com.unirios.gspi.entidades.Servico;
import com.unirios.gspi.repositorios.RepositorioFuncionario;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoFuncionario {
	

	@Autowired
	private RepositorioFuncionario repo;
	
	public List<Funcionario> findAll(){
		return repo.findAll();
	}
	
	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));	
	}
	
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Funcionario update(Funcionario obj) {
		Funcionario newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public Funcionario delete(Long id) {
		Funcionario obj = findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return obj;
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um funcionário que possui relação com Ordem de serviço");
		}
	}
	
	public Funcionario fromDto(FuncionarioDTO objDTO) {
		return new Funcionario(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), objDTO.getWhatsApp(), objDTO.getCargo(), objDTO.getDepartamento(), objDTO.getLogin(), objDTO.getSenha());
	}
	
	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setNome(obj.getNome());
		newObj.setCargo(obj.getCargo());
		newObj.setDepartamento(obj.getDepartamento());
		newObj.setEmail(obj.getEmail());
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
		newObj.setWhatsApp(obj.getWhatsApp());
	}
	
	public Page<Funcionario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarServicosPaginados(field.toLowerCase(),pageRequest);
		//return repo.findAll(pageRequest);

	}
	
}
