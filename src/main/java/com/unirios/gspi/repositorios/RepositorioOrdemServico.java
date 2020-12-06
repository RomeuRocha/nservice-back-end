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
	
	//@Query("SELECT COUNT(obj.id) FROM OrdemServico obj WHERE obj.situation = 1")
	//@Query("SELECT COUNT(os.SITUATION), MONTH(os.DATE_SCHEDULE) FROM ORDEM_DE_SERVICO os WHERE os.SITUATION = 4 Group By MONTH(os.DATE_SCHEDULE)")
	//@Query("FROM OrdemServico os WHERE YEAR(os.saveMoment) = :ano AND os.situation = 4 Order by MONTH(os.saveMoment")
	@Query("FROM OrdemServico os WHERE os.situation = :valor Order by MONTH(os.saveMoment)")
	public List<OrdemServico> osPorMes(Integer valor);
}
