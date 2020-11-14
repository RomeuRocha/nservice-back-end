package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Cliente;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Long>{

	@Query("FROM Cliente obj WHERE LOWER(obj.nome) like %:field%")
	public Page<Cliente> listarServicosPaginados(String field, Pageable  pageable );
	
}
