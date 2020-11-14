package com.unirios.gspi.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.OrdemServico;

@Repository
public interface RepositorioOrdemServico extends JpaRepository<OrdemServico, Long>{
	
	@Query("SELECT obj FROM OrdemServico obj"
			+ " JOIN FETCH obj.servicesItens it"
			+ " JOIN FETCH it.service"
			+ " JOIN FETCH obj.cliente"
			+ " JOIN FETCH obj.collaborator"
			+ " JOIN FETCH obj.subject"
			+ " WHERE obj IN :ordens")
	List<OrdemServico> findByServicesItens(List<OrdemServico> ordens);
	
	@Query("SELECT obj FROM OrdemServico obj"
			+ " JOIN FETCH obj.servicesItens it"
			+ " JOIN FETCH it.service"
			+ " JOIN FETCH obj.cliente"
			+ " JOIN FETCH obj.collaborator"
			+ " JOIN FETCH obj.subject")
	List<OrdemServico> findByServicesItensJoin();
	
	@Query("FROM OrdemServico obj WHERE LOWER(obj.cliente.nome) like %:field%")
	public Page<OrdemServico> listarServicosPaginados(String field, Pageable  pageable );
	
	
}
