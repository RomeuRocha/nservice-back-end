package com.unirios.gspi.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unirios.gspi.entities.Cliente;
import com.unirios.gspi.entities.ClienteFisico;
import com.unirios.gspi.entities.ClienteJuridico;
import com.unirios.gspi.entities.Collaborator;
import com.unirios.gspi.entities.OrderOfService;
import com.unirios.gspi.entities.ItemService;
import com.unirios.gspi.entities.Service;
import com.unirios.gspi.entities.Subject;
import com.unirios.gspi.entities.Enuns.Departamento;
import com.unirios.gspi.entities.Enuns.Status;
import com.unirios.gspi.repositories.ClienteRepository;
import com.unirios.gspi.repositories.CollaboratorRepository;
import com.unirios.gspi.repositories.OrderOfServiceRepository;
import com.unirios.gspi.repositories.ItemServiceRepository;
import com.unirios.gspi.repositories.ServiceRepository;
import com.unirios.gspi.repositories.SubjectRepository;

@Configuration
@Profile("test")
public class Instantiation implements CommandLineRunner{

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private CollaboratorRepository responsibleRepository;
	
	@Autowired
	private OrderOfServiceRepository orderOfServiceRepository;
	
	@Autowired
	private ItemServiceRepository itemServiceRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Service s1 = new Service(null,"Configuração do roteador",0.0f);
		Service s2 = new Service(null,"Cripagem de cabos",10.0f);
		Service s3 = new Service(null,"Instalação",100.0f);

		
		serviceRepository.saveAll(Arrays.asList(s1,s2,s3));
		
		Subject sub1 = new Subject(null, "Instalação de fibra");
		Subject sub2 = new Subject(null, "Instalação cabo par trançado");
		Subject sub3 = new Subject(null, "Sem conexão");
		
		subjectRepository.saveAll(Arrays.asList(sub1,sub2,sub3));
		
		Collaborator resp1 = new Collaborator(null, "João Felipe da Silva", "joão@teste.com", "0354444", "Técnico em redes", Departamento.SUPPORT, "joao_silva", "password");
		Collaborator resp2 = new Collaborator(null, "Kowalski", "kowalski@teste.com", "04151321", "Técnico em redes", Departamento.ADIMINISTRATION, "relatorio", "capitão");
		Collaborator resp3 = new Collaborator(null, "Tobirama Senju", "anti_uchiha@teste.com", "0354444", "Atendente", Departamento.ATTENDANCE, "tobirama", "death_uchiha");
		
		responsibleRepository.saveAll(Arrays.asList(resp1,resp2,resp3));
		
		OrderOfService os1 = new OrderOfService(null, resp1, sub1, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.SCHEDULED);
		OrderOfService os2 = new OrderOfService(null, resp2, sub2, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.CONCLUDED);
		OrderOfService os3 = new OrderOfService(null, resp3, sub3, Instant.parse("2020-10-13T19:53:07Z"), Instant.parse("2020-10-15T19:53:07Z"), null, Status.CANCELED);
		
		ItemService osItem1 = new ItemService(s1, os1, 0f);
		ItemService osItem2 = new ItemService(s2, os2, 10f);
		ItemService osItem3 = new ItemService(s3, os3, 100f);
		
	
		os1.AddItemService(osItem1);
		os2.AddItemService(osItem2);
		os3.AddItemService(osItem3);
		
		orderOfServiceRepository.saveAll(Arrays.asList(os1,os2,os3));
		
		itemServiceRepository.saveAll(Arrays.asList(osItem1,osItem2,osItem3));
		
		Cliente c1 = new ClienteFisico(null,"Maria silva","1236540","000000","000515151",Date.from(Instant.parse("1995-04-14T19:53:07Z")));
		Cliente c2 = new ClienteFisico(null,"Joana Costa","654987","111111","000515151",Date.from(Instant.parse("1995-04-14T19:53:07Z")));
		Cliente c3 = new ClienteJuridico(null, "UniRios", "321159", "45- 1213123", "1234564\0001", "UNIRIOS ME", "121a45\0", "103.03213.1");
		Cliente c4 = new ClienteJuridico(null, "Acer", "888888", "0800-1213123", "987451\0001", "ACER ME", "456165\0", "459231");
	
		
		
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		os1.setCliente(c1);
		os2.setCliente(c2);
		os3.setCliente(c3);
		orderOfServiceRepository.saveAll(Arrays.asList(os1,os2,os3));
		
	}

}
