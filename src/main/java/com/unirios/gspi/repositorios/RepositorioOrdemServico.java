package com.unirios.gspi.repositorios;

import java.time.Instant;
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
			+ " JOIN FETCH obj.assunto"
			+ " WHERE obj IN :ordens")
	List<OrdemServico> findByServicesItens(List<OrdemServico> ordens);
	
	@Query("SELECT obj FROM OrdemServico obj"
			+ " JOIN FETCH obj.servicesItens it"
			+ " JOIN FETCH it.service"
			+ " JOIN FETCH obj.cliente"
			+ " JOIN FETCH obj.collaborator"
			+ " JOIN FETCH obj.assunto")
	List<OrdemServico> findByServicesItensJoin();
	
	@Query("FROM OrdemServico obj WHERE LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%"
			+ " AND obj.saveMoment >= :dataInicial"
			+ " AND obj.saveMoment <= :dataFinal")
			
	public Page<OrdemServico> findOSByClienteAndAssunto(String cliente,String assunto,Instant dataInicial,Instant dataFinal, Pageable  pageable );
	
	@Query("FROM OrdemServico obj WHERE "
			+ " LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%"
			+ " AND obj.situation = :situacao")
	public Page<OrdemServico> findOSByClienteAndAssuntoAndSituacao(String cliente,String assunto,Integer situacao, Pageable  pageable );
	
	@Query("FROM OrdemServico obj WHERE "
			+ " LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%"
			+ " AND obj.saveMoment >= :dataInicial"
			+ " AND obj.saveMoment <= :dataFinal")
	public Page<OrdemServico> findOSByClienteAndAssuntoAndDataInicial(String cliente,String assunto,Instant dataInicial,Instant dataFinal, Pageable  pageable );
	
	@Query("FROM OrdemServico obj WHERE "
			+ " LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%"
			+ " AND obj.situation = :situacao"
			+ " AND obj.saveMoment >= :dataInicial"
			+ " AND obj.saveMoment <= :dataFinal")
	public Page<OrdemServico> findOSByClienteAndAssuntoAndSituacaoAndDataInicial(String cliente,String assunto,Integer situacao,Instant dataInicial,Instant dataFinal, Pageable  pageable );
	
	@Query("FROM OrdemServico obj WHERE "
			+ " LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%"
			+ " AND obj.situation = :situacao"
			+ " AND obj.saveMoment <= :dataFinal")
	public Page<OrdemServico> findOSByClienteAndAssuntoAndSituacaoAndDataFinal(String cliente,String assunto,Integer situacao,Instant dataFinal, Pageable  pageable );
	
	@Query("FROM OrdemServico obj WHERE "
			+ " LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%" 
			+ " AND obj.saveMoment <= :dataFinal")
	public Page<OrdemServico> findOSByClienteAndAssuntoAndSituacaoAndDataFinal(String cliente,String assunto,Instant dataFinal, Pageable  pageable );

	@Query("FROM OrdemServico obj WHERE "
			+ " LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%"
			+ " AND obj.saveMoment >= :dataInicial"
			+ " AND obj.saveMoment <= :dataFinal")
	public Page<OrdemServico> findOSByClienteAndAssuntoAndDataInicialAndDataFinal(String cliente,String assunto,Instant dataInicial,Instant dataFinal, Pageable  pageable );
	
	@Query("FROM OrdemServico obj WHERE "
			+ " LOWER(obj.cliente.nome) like %:cliente%"
			+ " AND LOWER(obj.assunto.description) like %:assunto%"
			+ " AND obj.situation = :situacao"
			+ " AND obj.saveMoment <= :dataFinal")
	public Page<OrdemServico> findOSByClienteAndAssuntoAndSituacaoAndDataInicialAndDataFinal(String cliente,String assunto,Integer situacao,Instant dataFinal, Pageable  pageable );
	
}
