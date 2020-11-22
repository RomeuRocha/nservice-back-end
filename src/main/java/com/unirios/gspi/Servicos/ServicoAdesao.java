package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.AdesaoDTO;
import com.unirios.gspi.entidades.Adesao;
import com.unirios.gspi.entidades.Endereco;
import com.unirios.gspi.repositorios.RepositorioAdesao;
import com.unirios.gspi.repositorios.RepositorioEndereco;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoAdesao {

	@Autowired
	private RepositorioAdesao repo;
	
	@Autowired
	private ServicoEndereco serviceEndereco;
	
	public List<Adesao> findAll(){
		return repo.findAll();
	}
	
	public Adesao findById(Long id) {
		Optional<Adesao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " +Adesao.class.getName()));	
	}
	
	public Adesao insert(Adesao obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Adesao update(Adesao obj) {
		Adesao newObj = findById(obj.getId());
		Endereco newEnd = serviceEndereco.findById(obj.getEndereco().getId());
		
		updateDataEndereco(newEnd,obj.getEndereco());
		serviceEndereco.update(newEnd);
		
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	private void updateDataEndereco(Endereco newEnd, Endereco endereco) {
		newEnd.setBairro(endereco.getBairro());
		newEnd.setCep(endereco.getCep());
		newEnd.setCidade(endereco.getCidade());
		newEnd.setNumero(endereco.getNumero());
		newEnd.setRua(endereco.getRua());
	}

	public Adesao delete(Long id) {
		Adesao a = findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return a;
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir umAdesao que possui relação com Ordem de serviço");
		}
	}
	
	public Adesao fromDto(AdesaoDTO subjectDTO) {
		return new Adesao(subjectDTO.getId(), subjectDTO.getData().toInstant(), subjectDTO.getValor(), subjectDTO.getPlano(), 
				subjectDTO.getCancelamento(), subjectDTO.getEndereco(), subjectDTO.getCliente());
	}
	
	private void updateData(Adesao newObj,Adesao obj) {
		newObj.setData(obj.getData());
		newObj.setValor(obj.getValor());
		newObj.setPlano(obj.getPlano());
		newObj.setCancelamento(obj.getCancelamento());
		newObj.setEndereco(obj.getEndereco());
		newObj.setCliente(obj.getCliente());
	}
	
	public Page<Adesao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarServicosPaginados(field.toLowerCase(),pageRequest);
		//return repo.findAll(pageRequest);

	}
	
}
