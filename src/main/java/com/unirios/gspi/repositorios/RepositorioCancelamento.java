package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Assunto;
import com.unirios.gspi.entidades.Cancelamento;

@Repository
public interface RepositorioCancelamento extends JpaRepository<Cancelamento, Long>{

	@Query("FROM Cancelamento obj WHERE LOWER(obj.motivo) like %:field%")
	public Page<Cancelamento> listarServicosPaginados(String field, Pageable  pageable );

	
	
}

