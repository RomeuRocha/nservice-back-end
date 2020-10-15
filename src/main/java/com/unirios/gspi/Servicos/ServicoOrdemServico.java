package com.unirios.gspi.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.entidades.ItemService;
import com.unirios.gspi.entidades.OrdemServico;
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
	
	public List<OrdemServico> findAll(){
		return repo.findAll();
	}
	
	public OrdemServico findById(Long id) {
		Optional<OrdemServico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));	
	}
	
	public OrdemServico insert(OrdemServico obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		
		obj = repo.save(obj);
		for(ItemService is: obj.getServicesItens()) {
			
			is.setValue(serviceRepository.findById(is.getService().getId()).get().getValue());
			is.setOrderOfService(obj);
			is.setService(is.getService());
		}
		itemRepo.saveAll(obj.getServicesItens());
		
		return obj;
	}
	
	public OrdemServico update(OrdemServico obj) {
		OrdemServico newObj = findById(obj.getId());
		
		for(ItemService is: newObj.getServicesItens()) {
			itemRepo.delete(is);//apaga os antigos itens
		}
		updateData(newObj, obj);
		
		for(ItemService is: newObj.getServicesItens()) {
			
			is.setValue(serviceRepository.findById(is.getService().getId()).get().getValue());
			is.setOrderOfService(newObj);
			is.setService(is.getService());
		}
		itemRepo.saveAll(newObj.getServicesItens());//salva os novos itens
		newObj = repo.save(newObj);
		
		return newObj;	
	}
	
	public void delete(Long id) {
		findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um funcionário que possui relação com Ordem de serviço");
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
	
	public Page<OrdemServico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
}
