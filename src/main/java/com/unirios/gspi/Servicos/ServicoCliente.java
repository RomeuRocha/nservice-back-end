package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.ClienteDTO;
import com.unirios.gspi.entidades.Cliente;
import com.unirios.gspi.repositorios.RepositorioCliente;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoCliente {

	@Autowired
	private RepositorioCliente repo;

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);// garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	public Cliente delete(Long id) {
		Cliente obj =  findById(id);// ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return obj;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir um Cliente que possui relação com Ordem de serviço");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarClientesPaginados(field.toLowerCase(),pageRequest);
	}

	private void updateData(Cliente newObj, Cliente obj) {

		newObj.setCpf(obj.getCpf());
		newObj.setNome(obj.getNome());
		newObj.setWhatsApp(obj.getWhatsApp());
		newObj.setEmail(obj.getEmail());

	}

	public Cliente fromDto(@Valid ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getEmail(), objDto.getCpf());
	}
}
