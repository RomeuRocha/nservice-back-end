package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Cliente;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Long>{

}