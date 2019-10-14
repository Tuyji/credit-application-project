package com.tuyji.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuyji.model.CreditApplication;

public interface CreditApplicationRepository extends MongoRepository<CreditApplication, String>{

}
