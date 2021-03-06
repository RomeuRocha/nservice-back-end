package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Servico;

@Repository
public interface RepositorioServico extends JpaRepository<Servico, Long>{

	@Query("FROM Servico obj WHERE LOWER(obj.description) like %:field%")
	public Page<Servico> listarServicosPaginados(String field, Pageable  pageable );
	
}
