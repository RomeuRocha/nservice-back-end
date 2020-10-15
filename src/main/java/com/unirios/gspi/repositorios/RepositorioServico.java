package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Servico;

@Repository
public interface RepositorioServico extends JpaRepository<Servico, Long>{

}
