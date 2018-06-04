package com.devopsbuddy.backend.persistence.repositoires;

import org.springframework.data.repository.CrudRepository;

import com.devopsbuddy.backend.persistence.domain.FeedBack;

public interface FeedBackRepository extends CrudRepository<FeedBack, Integer>{

}
