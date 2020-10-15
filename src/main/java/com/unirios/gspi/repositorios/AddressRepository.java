package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unirios.gspi.entities.Endereco;

public interface AddressRepository extends JpaRepository<Endereco, Long>{

}
