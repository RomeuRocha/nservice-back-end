package com.unirios.gspi.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Plano;

@Repository
public interface RepositorioPlano extends JpaRepository<Plano, Long>{

	@Query("FROM Plano obj WHERE LOWER(obj.descricao) like %:field%")
	public Page<Plano> listarPlanosPaginados(String field, Pageable  pageable );
}
