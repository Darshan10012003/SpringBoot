package com.serviceuser.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceuser.entities.EducationDetails;
@Repository
public interface EducationDetailsRepo extends CrudRepository<EducationDetails, Long>{

}
