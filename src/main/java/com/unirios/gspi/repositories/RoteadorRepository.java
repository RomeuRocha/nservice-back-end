package com.unirios.gspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unirios.gspi.entities.Service;

public interface RoteadorRepository extends JpaRepository<Service, Long>{

}