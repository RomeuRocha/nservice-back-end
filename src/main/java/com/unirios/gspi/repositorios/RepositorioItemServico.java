package com.unirios.gspi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entidades.ItemService;

@Repository
public interface RepositorioItemServico extends JpaRepository<ItemService, Long>{

}
