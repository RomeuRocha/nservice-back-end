package com.unirios.gspi.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unirios.gspi.entidades.Assunto;
import com.unirios.gspi.entidades.Cliente;
import com.unirios.gspi.entidades.Funcionario;
import com.unirios.gspi.entidades.ItemService;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.entidades.Servico;
import com.unirios.gspi.entities.Enuns.Departamento;
import com.unirios.gspi.entities.Enuns.Status;
import com.unirios.gspi.repositorios.RepositorioAssunto;
import com.unirios.gspi.repositorios.RepositorioCliente;
import com.unirios.gspi.repositorios.RepositorioFuncionario;
import com.unirios.gspi.repositorios.RepositorioItemServico;
import com.unirios.gspi.repositorios.RepositorioOrdemServico;
import com.unirios.gspi.repositorios.RepositorioServico;

@Configuration
@Profile("test")
public class Instantiation implements CommandLineRunner{

	@Autowired
	private RepositorioServico serviceRepository;
	
	@Autowired
	private RepositorioAssunto subjectRepository;
	
	@Autowired
	private RepositorioFuncionario responsibleRepository;
	
	@Autowired
	private RepositorioOrdemServico orderOfServiceRepository;
	
	@Autowired
	private RepositorioItemServico itemServiceRepository;
	
	@Autowired
	private RepositorioCliente clienteRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Servico s1 = new Servico(null,"Configuração do roteador",0.0f);
		Servico s2 = new Servico(null,"Cripagem de cabos",10.0f);
		Servico s3 = new Servico(null,"Instalação",100.0f);

		
		serviceRepository.saveAll(Arrays.asList(s1,s2,s3));
		
		Assunto sub1 = new Assunto(null, "Instalação de fibra");
		Assunto sub2 = new Assunto(null, "Instalação cabo par trançado");
		Assunto sub3 = new Assunto(null, "Sem conexão");
		
		subjectRepository.saveAll(Arrays.asList(sub1,sub2,sub3));
		
		Funcionario resp1 = new Funcionario(null, "João Felipe da Silva", "joão@teste.com", "0354444", "Técnico em redes", Departamento.SUPPORT, "joao_silva", "password");
		Funcionario resp2 = new Funcionario(null, "Kowalski", "kowalski@teste.com", "04151321", "Técnico em redes", Departamento.ADIMINISTRATION, "relatorio", "capitão");
		Funcionario resp3 = new Funcionario(null, "Tobirama Senju", "anti_uchiha@teste.com", "0354444", "Atendente", Departamento.ATTENDANCE, "tobirama", "death_uchiha");
		
		responsibleRepository.saveAll(Arrays.asList(resp1,resp2,resp3));
		
		OrdemServico os1 = new OrdemServico(null, resp1, null, sub1, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.SCHEDULED);
		OrdemServico os2 = new OrdemServico(null, resp2, null, sub2, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.CONCLUDED);
		OrdemServico os3 = new OrdemServico(null, resp3, null, sub3, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.CANCELED);
		
		//OrdemServico os4 = new OrdemServico(null, resp1, sub1, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.SCHEDULED);
		//OrdemServico os5 = new OrdemServico(null, resp2, sub2, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.CONCLUDED);
		//OrdemServico os6 = new OrdemServico(null, resp3, sub3, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.CANCELED);
		
		ItemService osItem1 = new ItemService(s1, os1, 0f);
		ItemService osItem2 = new ItemService(s2, os2, 10f);
		ItemService osItem3 = new ItemService(s3, os3, 100f);
		
	
		os1.AddItemService(osItem1);
		os2.AddItemService(osItem2);
		os3.AddItemService(osItem3);
		
		orderOfServiceRepository.saveAll(Arrays.asList(os1,os2,os3/*,os4,os5,os6*/));
		
		itemServiceRepository.saveAll(Arrays.asList(osItem1,osItem2,osItem3));
		
		Cliente c1 = new Cliente(null, "José da Silva", "032323223", "jose@teste.com","786.355.400-42");
		Cliente c2 = new Cliente(null, "Maria Ferreira", "032323223","maria@teste.com","786.355.400-42");
		Cliente c3 = new Cliente(null, "Ana Clara", "032323223","ana@teste.com","786.355.400-42");
		Cliente c4 = new Cliente(null, "Romeu Rocha", "032323223","romeu@teste.com","786.355.400-42");
		
		
		
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		os1.setCliente(c1);
		os2.setCliente(c2);
		os3.setCliente(c3);
		orderOfServiceRepository.saveAll(Arrays.asList(os1,os2,os3));
		
	}

}
