package com.tuyji.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuyji.model.CreditScore;
import com.tuyji.repository.CreditScoreRepository;

@Service
public class CreditScoreService {

	@Autowired
	CreditScoreRepository creditScoreRepository;
	
	public Optional<CreditScore> findById(String id) {
		return creditScoreRepository.findById(id);
	}
}
