package com.unirios.gspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unirios.gspi.entities.Endereco;

public interface AddressRepository extends JpaRepository<Endereco, Long>{

}
