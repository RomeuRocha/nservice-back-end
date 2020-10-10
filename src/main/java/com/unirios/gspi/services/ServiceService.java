package com.unirios.gspi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.unirios.gspi.dto.ServiceDTO;
import com.unirios.gspi.entities.Service;
import com.unirios.gspi.repositories.ServiceRepository;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@org.springframework.stereotype.Service
public class ServiceService {

	
	@Autowired
	private ServiceRepository repo;
	
	public List<Service> findAll(){
		return repo.findAll();
	}
	
	public Service findById(Long id) {
		Optional<Service> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Service.class.getName()));	
	}
	
	public Service insert(Service obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Service fromDto(ServiceDTO serviDTO) {
		return new Service(serviDTO.getId(), serviDTO.getDescription(), serviDTO.getValue());
	}
	
	
}
