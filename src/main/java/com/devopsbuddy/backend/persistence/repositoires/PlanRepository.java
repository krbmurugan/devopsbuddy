package com.devopsbuddy.backend.persistence.repositoires;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devopsbuddy.backend.persistence.domain.Plan;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer>{

}
