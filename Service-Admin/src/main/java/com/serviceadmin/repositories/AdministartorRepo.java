package com.serviceadmin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceadmin.entities.Administartor;
@Repository
public interface AdministartorRepo extends CrudRepository<Administartor, Long> {

}
