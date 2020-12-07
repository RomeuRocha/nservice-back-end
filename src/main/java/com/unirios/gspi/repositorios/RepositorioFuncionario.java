package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Funcionario;

@Repository
public interface RepositorioFuncionario extends JpaRepository<Funcionario, Long>{

	@Query("FROM Funcionario obj WHERE LOWER(obj.nome) like %:field%")
	public Page<Funcionario> listarFuncionariosPaginados(String field, Pageable  pageable );
	
}
