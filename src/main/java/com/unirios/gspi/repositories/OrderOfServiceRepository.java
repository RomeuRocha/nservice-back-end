package com.unirios.gspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.OrderOfService;

@Repository
public interface OrderOfServiceRepository extends JpaRepository<OrderOfService, Long>{

}
