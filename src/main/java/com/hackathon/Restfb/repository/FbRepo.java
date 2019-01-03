package com.hackathon.Restfb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.Restfb.details.Details;

@Repository
public interface FbRepo extends CrudRepository<Details, Integer>{
	Iterable<Details> findAll();

}
