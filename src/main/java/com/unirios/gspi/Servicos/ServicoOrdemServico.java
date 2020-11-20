package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.OrdemServicoDTO;
import com.unirios.gspi.dto.ServicoDTO;
import com.unirios.gspi.entidades.ItemService;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.entidades.Servico;
import com.unirios.gspi.repositorios.RepositorioItemServico;
import com.unirios.gspi.repositorios.RepositorioOrdemServico;
import com.unirios.gspi.repositorios.RepositorioServico;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoOrdemServico {

	@Autowired
	private RepositorioOrdemServico repo;

	@Autowired
	private RepositorioItemServico itemRepo;

	@Autowired
	private RepositorioServico serviceRepository;

	public List<OrdemServico> findAll() {
		return repo.findAll();
	}

	public OrdemServico findById(Long id) {
		Optional<OrdemServico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
	}

	public OrdemServico insert(OrdemServico obj) {
		obj.setId(null);// garantir o insert, pois se tiver id, ele faz update

		obj = repo.save(obj);
		for (ItemService is : obj.getServicesItens()) {

			is.setValue(serviceRepository.findById(is.getService().getId()).get().getValue());
			is.setOrderService(obj);
			is.setService(is.getService());
		}
		itemRepo.saveAll(obj.getServicesItens());

		return obj;
	}

	public OrdemServico update(OrdemServico obj) {
		OrdemServico newObj = findById(obj.getId());

		for (ItemService is : newObj.getServicesItens()) {
			itemRepo.delete(is);// apaga os antigos itens
		}
		updateData(newObj, obj);

		for (ItemService is : newObj.getServicesItens()) {

			is.setValue(serviceRepository.findById(is.getService().getId()).get().getValue());
			is.setOrderService(newObj);
			is.setService(is.getService());
		}
		itemRepo.saveAll(newObj.getServicesItens());// salva os novos itens
		newObj = repo.save(newObj);

		return newObj;
	}

	public OrdemServico delete(Long id) {
		OrdemServico obj = findById(id);// ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return obj;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir um funcionário que possui relação com Ordem de serviço");
		}
	}

	private void updateData(OrdemServico newObj, OrdemServico obj) {
		newObj.setAttendance(obj.getAttendance());
		newObj.setCollaborator(obj.getCollaborator());
		newObj.setDateSchedule(obj.getDateSchedule());
		newObj.setServicesItens(obj.getServicesItens());
		newObj.setSaveMoment(obj.getSaveMoment());
		newObj.setSituation(obj.getSituation());
		newObj.setSubject(obj.getSubject());
		newObj.setCliente(obj.getCliente());
	}
	
	
	public Page<OrdemServico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String cliente,String assunto) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		repo.findByServicesItensJoin();
		Page<OrdemServico> pages = repo.listarServicosPaginados(cliente.toLowerCase(),assunto.toLowerCase(),pageRequest);

		return pages;
	}

	public OrdemServico fromDTO(OrdemServicoDTO dto) {
		OrdemServico os = new OrdemServico(dto.getId(), dto.getFuncionario(),dto.getCliente(), dto.getAssunto(), dto.getSaveMoment(),
				dto.getDateSchedule(), dto.getAttendance(), dto.getSituation());
		for(ServicoDTO servDTO : dto.getServicos()) {
			Servico s = new Servico(servDTO.getId(), servDTO.getDescription(), servDTO.getValue());
			os.getServicesItens().add(new ItemService(s, os, s.getValue()));
		}
		return os;
	}

}
