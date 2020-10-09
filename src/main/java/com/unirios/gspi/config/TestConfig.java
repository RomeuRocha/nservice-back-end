package com.unirios.gspi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unirios.gspi.entities.Service;
import com.unirios.gspi.repositories.ServiceRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Service s1 = new Service(null,"Configuração do roteador",0.0f);
		Service s2 = new Service(null,"Cripagem de cabos",0.0f);
		Service s3 = new Service(null,"Instalação",100.0f);

		
		serviceRepository.saveAll(Arrays.asList(s1,s2,s3));
		
		
	}

}
