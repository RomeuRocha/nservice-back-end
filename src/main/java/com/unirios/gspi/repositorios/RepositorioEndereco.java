package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unirios.gspi.entidades.Endereco;

public interface RepositorioEndereco extends JpaRepository<Endereco, Long>{

	@Query("FROM Endereco obj WHERE LOWER(obj.rua) like %:field%")
	public Page<Endereco> listarServicosPaginados(String field, Pageable  pageable );
	
}
