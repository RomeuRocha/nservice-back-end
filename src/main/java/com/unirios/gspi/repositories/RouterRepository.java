package com.unirios.gspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Router;


public interface RouterRepository extends JpaRepository<Router, Long>{

}
