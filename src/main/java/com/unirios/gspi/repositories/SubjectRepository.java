package com.unirios.gspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unirios.gspi.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}

