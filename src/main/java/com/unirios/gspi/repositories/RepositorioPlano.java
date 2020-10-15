package com.unirios.gspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Plano;

@Repository
public interface RepositorioPlano extends JpaRepository<Plano, Long>{

}
