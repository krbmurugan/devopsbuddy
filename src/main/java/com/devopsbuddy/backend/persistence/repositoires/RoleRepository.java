package com.devopsbuddy.backend.persistence.repositoires;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devopsbuddy.backend.persistence.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
