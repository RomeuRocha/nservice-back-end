package com.unirios.gspi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unirios.gspi.entities.Service;
import com.unirios.gspi.repositories.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {

	
	@Autowired
	private ServiceRepository repo;
	
	public List<Service> findAll(){
		return repo.findAll();
	}
	
}
