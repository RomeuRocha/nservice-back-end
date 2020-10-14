package com.unirios.gspi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.entities.Cliente;
import com.unirios.gspi.entities.ClienteFisico;
import com.unirios.gspi.entities.ClienteJuridico;
import com.unirios.gspi.repositories.ClienteRepository;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

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

	public void delete(Long id) {
		findById(id);// ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir um Cliente que possui relação com Ordem de serviço");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	private void updateData(Cliente newObj, Cliente obj) {
		if (newObj instanceof ClienteFisico) {
			ClienteFisico cf = (ClienteFisico) obj;
			((ClienteFisico) newObj).setCpf(cf.getCpf());
			((ClienteFisico) newObj).setDataNascimento(cf.getDataNascimento());
			newObj.setNome(cf.getNome());
			newObj.setTelefone(cf.getTelefone());
			newObj.setWhatsApp(cf.getWhatsApp());

		} else {
			ClienteJuridico cj = (ClienteJuridico) obj;
			((ClienteJuridico) newObj).setCnpj(cj.getCnpj());
			((ClienteJuridico) newObj).setInscricaoEstadual(cj.getInscricaoEstadual());
			((ClienteJuridico) newObj).setInscricaoMunicipal(cj.getInscricaoMunicipal());
			((ClienteJuridico) newObj).setRazaoSocial(cj.getRazaoSocial());
			((ClienteJuridico) newObj).setNome(cj.getNome());
			((ClienteJuridico) newObj).setTelefone(cj.getTelefone());
			((ClienteJuridico) newObj).setWhatsApp(cj.getWhatsApp());

		}
	}
}
