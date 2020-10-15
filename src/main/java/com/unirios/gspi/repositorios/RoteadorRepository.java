package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Router;

@Repository
public interface RoteadorRepository extends JpaRepository<Router, Long>{

}
