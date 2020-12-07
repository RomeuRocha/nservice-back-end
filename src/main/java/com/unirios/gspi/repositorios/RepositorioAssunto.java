package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Assunto;

@Repository
public interface RepositorioAssunto extends JpaRepository<Assunto, Long>{

	@Query("FROM Assunto obj WHERE LOWER(obj.description) like %:field%")
	public Page<Assunto> listarAssuntosPaginados(String field, Pageable  pageable );
	
}

