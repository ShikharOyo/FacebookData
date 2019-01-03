package com.hackathon.Restfb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.Restfb.details.Details;
import com.hackathon.Restfb.details.Details2;

@Repository
public interface FbRepo2 extends CrudRepository<Details2, Integer>{
	
	Iterable<Details2> findAll();
}
