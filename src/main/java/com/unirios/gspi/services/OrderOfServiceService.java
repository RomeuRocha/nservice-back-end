package com.unirios.gspi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.entities.ItemService;
import com.unirios.gspi.entities.OrderOfService;
import com.unirios.gspi.repositories.ItemServiceRepository;
import com.unirios.gspi.repositories.OrderOfServiceRepository;
import com.unirios.gspi.repositories.ServiceRepository;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class OrderOfServiceService {
	
	@Autowired
	private OrderOfServiceRepository repo;
	
	@Autowired
	private ItemServiceRepository itemRepo;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	public List<OrderOfService> findAll(){
		return repo.findAll();
	}
	
	public OrderOfService findById(Long id) {
		Optional<OrderOfService> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrderOfService.class.getName()));	
	}
	
	public OrderOfService insert(OrderOfService obj) {
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
	
	public OrderOfService update(OrderOfService obj) {
		OrderOfService newObj = findById(obj.getId());
		
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
	
	private void updateData(OrderOfService newObj, OrderOfService obj) {
		newObj.setAttendance(obj.getAttendance());
		newObj.setCollaborator(obj.getCollaborator());
		newObj.setDateSchedule(obj.getDateSchedule());
		newObj.setServicesItens(obj.getServicesItens());
		newObj.setSaveMoment(obj.getSaveMoment());
		newObj.setSituation(obj.getSituation());
		newObj.setSubject(obj.getSubject());	
	}
	
	public Page<OrderOfService> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
}
