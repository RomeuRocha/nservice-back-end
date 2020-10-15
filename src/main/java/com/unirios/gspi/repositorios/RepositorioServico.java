package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Service;

@Repository
public interface RepositorioServico extends JpaRepository<Service, Long>{

}