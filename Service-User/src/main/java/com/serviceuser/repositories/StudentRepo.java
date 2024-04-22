package com.serviceuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceuser.entities.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,String> {
	
}
