package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Subject;

@Repository
public interface RepositorioAssunto extends JpaRepository<Subject, Long>{

}

