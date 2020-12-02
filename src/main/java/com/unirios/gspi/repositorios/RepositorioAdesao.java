package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Adesao;

@Repository
public interface RepositorioAdesao extends JpaRepository<Adesao, Long>{

	@Query("FROM Adesao obj WHERE LOWER(obj.cliente.nome) like %:field%")
	public Page<Adesao> listarServicosPaginados(String field, Pageable  pageable );

	
}

