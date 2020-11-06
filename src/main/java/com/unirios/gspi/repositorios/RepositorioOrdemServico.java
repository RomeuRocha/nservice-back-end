package com.unirios.gspi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.OrdemServico;

@Repository
public interface RepositorioOrdemServico extends JpaRepository<OrdemServico, Long>{
	
	@Query("SELECT obj FROM OrdemServico obj"
			+ " INNER JOIN FETCH obj.servicesItens it"
			+ " INNER JOIN FETCH it.service"
			+ " INNER JOIN FETCH obj.cliente"
			+ " INNER JOIN FETCH obj.collaborator"
			+ " INNER JOIN FETCH obj.subject")
	List<OrdemServico> findJoinOs();
	
	
	@Query("SELECT obj FROM OrdemServico obj"
			+ " INNER JOIN FETCH obj.servicesItens"
			+ " WHERE obj IN :listaOs")
	List<OrdemServico> findJoinOsParametro(List<OrdemServico> listaOs);
}
