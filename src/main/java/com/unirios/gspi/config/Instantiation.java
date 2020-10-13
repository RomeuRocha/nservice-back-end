package com.unirios.gspi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unirios.gspi.entities.Collaborator;
import com.unirios.gspi.entities.Service;
import com.unirios.gspi.entities.Subject;
import com.unirios.gspi.entities.Enuns.Departamento;
import com.unirios.gspi.repositories.CollaboratorRepository;
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
	
	@Override
	public void run(String... args) throws Exception {
		
		Service s1 = new Service(null,"Configuração do roteador",0.0f);
		Service s2 = new Service(null,"Cripagem de cabos",0.0f);
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

	}

}
