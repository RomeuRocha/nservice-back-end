package com.unirios.gspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Roteador;
import com.unirios.gspi.entities.Service;

@Repository
public interface RepositorioRoteador extends JpaRepository<Roteador, Long>{

}
