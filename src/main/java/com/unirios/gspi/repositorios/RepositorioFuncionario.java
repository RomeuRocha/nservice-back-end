package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.Funcionario;

@Repository
public interface RepositorioFuncionario extends JpaRepository<Funcionario, Long>{

}
