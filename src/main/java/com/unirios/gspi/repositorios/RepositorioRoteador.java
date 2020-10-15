package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Roteador;


@Repository
public interface RepositorioRoteador extends JpaRepository<Roteador, Long>{

}
