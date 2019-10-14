package com.tuyji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuyji.model.CreditApplication;
import com.tuyji.repository.CreditApplicationRepository;

@Service
public class CreditApplicationService {

	@Autowired
	CreditApplicationRepository creditApplicationRepository;
	
	public CreditApplication addCreditApplication(CreditApplication creditApplication){
		return creditApplicationRepository.insert(creditApplication);
	}
	
}
