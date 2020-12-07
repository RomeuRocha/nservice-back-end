package com.unirios.gspi.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unirios.gspi.entidades.Adesao;
import com.unirios.gspi.entidades.Assunto;
import com.unirios.gspi.entidades.Cliente;
import com.unirios.gspi.entidades.Endereco;
import com.unirios.gspi.entidades.Funcionario;
import com.unirios.gspi.entidades.ItemService;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.entidades.Plano;
import com.unirios.gspi.entidades.Servico;
import com.unirios.gspi.entities.Enuns.Departamento;
import com.unirios.gspi.entities.Enuns.Estado;
import com.unirios.gspi.entities.Enuns.Status;
import com.unirios.gspi.repositorios.RepositorioAdesao;
import com.unirios.gspi.repositorios.RepositorioAssunto;
import com.unirios.gspi.repositorios.RepositorioCliente;
import com.unirios.gspi.repositorios.RepositorioEndereco;
import com.unirios.gspi.repositorios.RepositorioFuncionario;
import com.unirios.gspi.repositorios.RepositorioItemServico;
import com.unirios.gspi.repositorios.RepositorioOrdemServico;
import com.unirios.gspi.repositorios.RepositorioPlano;
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
	
	@Autowired
	private RepositorioPlano planorepository;
	
	@Autowired
	private RepositorioEndereco enderecoRepository;
	
	
	@Autowired
	private RepositorioAdesao adesaoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Servico s1 = new Servico(null,"Configuração do roteador",0.0f);
		Servico s2 = new Servico(null,"Cripagem de cabos",10.0f);
		Servico s3 = new Servico(null,"Instalação",100.0f);

		
		serviceRepository.saveAll(Arrays.asList(s1,s2,s3));
		
		Assunto sub1 = new Assunto(null, "Instalação de fibra");
		Assunto sub2 = new Assunto(null, "Instalação cabo par trançado");
		Assunto sub3 = new Assunto(null, "Sem conexão");
		Assunto sub4 = new Assunto(null, "Conexão lenta");
		
		subjectRepository.saveAll(Arrays.asList(sub1,sub2,sub3,sub4));
		
		Funcionario resp1 = new Funcionario(null, "João Felipe da Silva", "joão@teste.com", "0354444", "Técnico em redes", Departamento.SUPPORT, "joao_silva", "password");
		Funcionario resp2 = new Funcionario(null, "Kowalski", "kowalski@teste.com", "04151321", "Técnico em redes", Departamento.ADIMINISTRATION, "relatorio", "capitão");
		Funcionario resp3 = new Funcionario(null, "Tobirama Senju", "anti_uchiha@teste.com", "0354444", "Atendente", Departamento.ATTENDANCE, "tobirama", "death_uchiha");
		
		responsibleRepository.saveAll(Arrays.asList(resp1,resp2,resp3));
		

		OrdemServico os1 = new OrdemServico(null, null, null, sub1, Instant.parse("2020-01-13T00:10:07Z"), Instant.parse("2020-10-14T09:53:07Z"), null, Status.AGENDADO);
		OrdemServico os2 = new OrdemServico(null, resp2, null, sub2, Instant.parse("2020-12-01T10:53:07Z"), Instant.parse("2020-10-15T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os3 = new OrdemServico(null, null, null, sub3, Instant.parse("2020-10-25T10:53:07Z"), Instant.parse("2020-10-17T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os4 = new OrdemServico(null, resp2, null, sub3, Instant.parse("2020-12-01T10:53:07Z"), Instant.parse("2020-10-20T09:53:07Z"), null, Status.AGENDADO);
		OrdemServico os5 = new OrdemServico(null, resp1, null, sub4, Instant.parse("2020-11-01T10:53:07Z"), Instant.parse("2020-10-05T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os6 = new OrdemServico(null, resp1, null, sub4, Instant.parse("2020-10-15T10:53:07Z"), Instant.parse("2020-12-08T09:53:07Z"), null, Status.AGENDADO);
		OrdemServico os7 = new OrdemServico(null, resp1, null, sub4, Instant.parse("2020-12-01T10:53:07Z"), Instant.parse("2020-10-01T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os8 = new OrdemServico(null, null, null, sub1, Instant.parse("2020-01-13T00:10:07Z"), Instant.parse("2020-11-14T09:53:07Z"), null, Status.AGENDADO);
		OrdemServico os9 = new OrdemServico(null, resp2, null, sub2, Instant.parse("2020-12-01T10:53:07Z"), Instant.parse("2020-11-14T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os10 = new OrdemServico(null, null, null, sub3, Instant.parse("2020-10-25T10:53:07Z"), Instant.parse("2020-12-02T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os11 = new OrdemServico(null, resp2, null, sub3, Instant.parse("2020-12-01T10:53:07Z"), Instant.parse("2020-12-22T09:53:07Z"), null, Status.AGENDADO);
		OrdemServico os12 = new OrdemServico(null, resp1, null, sub4, Instant.parse("2020-11-01T10:53:07Z"), Instant.parse("2020-12-05T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os13 = new OrdemServico(null, resp1, null, sub4, Instant.parse("2020-10-15T10:53:07Z"), Instant.parse("2020-11-30T09:53:07Z"), null, Status.CONCLUIDO);
		OrdemServico os14 = new OrdemServico(null, resp1, null, sub4, Instant.parse("2020-12-01T10:53:07Z"), Instant.parse("2020-11-05T09:53:07Z"), null, Status.CONCLUIDO);

		
		
		
		ItemService osItem1 = new ItemService(s1, os1, 0f);
		ItemService osItem2 = new ItemService(s2, os2, 10f);
		ItemService osItem3 = new ItemService(s3, os3, 100f);
		
	
		os1.AddItemService(osItem1);
		os2.AddItemService(osItem2);
		os3.AddItemService(osItem3);
		
		orderOfServiceRepository.saveAll(Arrays.asList(os1,os2,os3,os4,os5,os6,os7));
		
		itemServiceRepository.saveAll(Arrays.asList(osItem1,osItem2,osItem3));
		
		Cliente c1 = new Cliente(null, "José da Silva", "032323223", "jose@teste.com","786.355.400-42");
		Cliente c2 = new Cliente(null, "Maria Ferreira", "032323223","maria@teste.com","786.355.400-42");
		Cliente c3 = new Cliente(null, "Ana Clara", "032323223","ana@teste.com","786.355.400-42");
		Cliente c4 = new Cliente(null, "Romeu Rocha", "032323223","romeu@teste.com","699.952.050-09");
		Cliente c5 = new Cliente(null, "Pedro da Marques", "032323223", "pedro.23@teste.com","786.355.400-42");
		Cliente c6 = new Cliente(null, "Maria Joaquina", "032323223","maria30@teste.com","660.396.600-07");
		Cliente c7 = new Cliente(null, "Ana Paula", "032323223","ana25@teste.com","699.952.050-09");
		Cliente c8 = new Cliente(null, "Roberio Lima", "032323223","romeu@teste.com","786.355.400-42");
		
		
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8));
		
		os1.setCliente(c1);
		os2.setCliente(c2);
		os3.setCliente(c3);
		os4.setCliente(c1);
		os5.setCliente(c3);
		os6.setCliente(c2);
		os7.setCliente(c4);
		os8.setCliente(c5);
		os9.setCliente(c6);
		os10.setCliente(c7);
		os11.setCliente(c8);
		os12.setCliente(c6);
		os13.setCliente(c8);
		os14.setCliente(c1);
		
		orderOfServiceRepository.saveAll(Arrays.asList(os1,os2,os3,os4,os5,os6,os7,os8,os9,os10,os11,os12,os13,os14));
		
		Plano p1 = new Plano(null, "20 MB", 50f, "Simples");
		Plano p2 = new Plano(null, "30 MB", 70f, "Premiun");
		Plano p3 = new Plano(null, "50 MB", 100f, "Master");
		Plano p4 = new Plano(null, "100 MB", 200f, "Empresarial");
		
		planorepository.saveAll(Arrays.asList(p1,p2,p3,p4));
		
		
		
		Endereco e1 = new Endereco(null, "rua a", "45", "Campo grande", "57480-000", "Delmiro Gouveia","AL");
		Endereco e2 = new Endereco(null, "rua b", "15", "Centro", "57480-000",  "Delmiro Gouveia","AL");
		Endereco e3 = new Endereco(null, "rua c", "225", "Campo grande", "57480-000",  "Delmiro Gouveia","AL");
		Endereco e4 = new Endereco(null, "rua d", "40", "Eldorado", "57480-000",  "Delmiro Gouveia","AL");
		Endereco e5 = new Endereco(null, "rua e", "145", "Campo grande", "57480-000",  "Delmiro Gouveia","AL");
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3,e4,e5));
		
		Adesao a1 = new Adesao(null,Instant.parse("2020-10-13T19:53:07Z"), p1, null, e2, c1);
		Adesao a2 = new Adesao(null,Instant.parse("2020-11-13T19:53:07Z"), p2, null, e1, c3);
		Adesao a3 = new Adesao(null,Instant.parse("2020-12-13T19:53:07Z"), p2, null, e3, c4);
		Adesao a4 = new Adesao(null,Instant.parse("2020-10-13T19:53:07Z"), p1, null, e5, c2);
		
		adesaoRepository.saveAll(Arrays.asList(a1,a2,a3,a4));
	}

}
