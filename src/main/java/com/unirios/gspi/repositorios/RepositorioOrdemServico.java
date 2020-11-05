package com.unirios.gspi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.OrdemServico;

@Repository
public interface RepositorioOrdemServico extends JpaRepository<OrdemServico, Long>{
	
	@Query("SELECT obj FROM OrdemServico obj JOIN FETCH obj.cliente "
			+ "JOIN FETCH obj.collaborator"
			+ " JOIN FETCH obj.subject"
			+ " JOIN FETCH obj.servicesItens")
	List<OrdemServico> listarTudo();

}
