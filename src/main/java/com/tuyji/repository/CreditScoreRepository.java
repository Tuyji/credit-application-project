package com.tuyji.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuyji.model.CreditScore;

public interface CreditScoreRepository extends MongoRepository<CreditScore, String>{

}
