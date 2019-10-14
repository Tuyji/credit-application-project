package com.tuyji.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tuyji.controller.response.CreditApplicationResponse;
import com.tuyji.model.CreditApplication;
import com.tuyji.model.CreditScore;
import com.tuyji.service.CreditApplicationService;
import com.tuyji.service.CreditScoreService;
import com.tuyji.service.SMSService;
import com.tuyji.util.CommonConstants;
import com.tuyji.util.HeaderUtil;

@RestController
public class CreditApplicationController {

	@Autowired
	CreditApplicationService creditApplicationService;

	@Autowired
	CreditScoreService creditScoreService;
	
	@Autowired
	SMSService smsService;

	@PostMapping("/creditapplication")
	public ResponseEntity<CreditApplicationResponse> addCreditApplication(@RequestBody CreditApplication creditApplication) {

		String identityNumber = creditApplication.getIdentityNumber();

		Optional<CreditScore> creditScoreOpt = creditScoreService.findById(identityNumber);

		if(! creditScoreOpt.isPresent())
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createErrorAlert("Credit Score is missing"))
		            .body(null);

		BigDecimal limit = calculateCreditApplicationLimit(creditApplication, 
				creditScoreOpt.get());
		
		String result = limit.equals(BigDecimal.ZERO) ? "denied" : "accepted";
		
		
		CreditApplication createCreditApplicationNew = createCreditApplicationEntity(creditApplication, 
				result, limit.toString());
		creditApplicationService.addCreditApplication(createCreditApplicationNew);
		
//		smsService.sendSMS(creditApplication.getPhonenumber(), result);
		
		return createResponseEntity(limit);

	}
	
    private CreditApplication createCreditApplicationEntity(CreditApplication creditApplication, String result, String creditLimit) {
    	CreditApplication creditApplicationNew = new CreditApplication(creditApplication.getIdentityNumber(), 
    			creditApplication.getName(), creditApplication.getSurname(), creditApplication.getIncome(),
    			creditApplication.getPhonenumber(), result, creditLimit);
		return creditApplicationNew;
	}

	private ResponseEntity<CreditApplicationResponse> createResponseEntity(BigDecimal limit) {
    	
    	String result = limit.equals(BigDecimal.ZERO) ? "DENIED" : "ACCEPTED";
    	
    	CreditApplicationResponse response = 
    			new CreditApplicationResponse(result, limit.toString());
    	
    	return ResponseEntity.ok().body(response);
		
	}

	private BigDecimal calculateCreditApplicationLimit(CreditApplication creditApplication, 
			CreditScore creditScore) {
		
		Integer creditScoreValue = Integer.valueOf(creditScore.getCreditScore());
		
		BigDecimal result = BigDecimal.ZERO;
		
		BigDecimal income = new BigDecimal(creditApplication.getIncome());
		
		if(creditScoreValue < CommonConstants.CREDIT_SCORE_BOUNDRY_FIRST)
			return result;
		
		if(CommonConstants.CREDIT_SCORE_BOUNDRY_FIRST <= creditScoreValue 
				&& creditScoreValue < CommonConstants.CREDIT_SCORE_BOUNDRY_SECOND
				&& compareIncome(income) == -1) {
			
			result = new BigDecimal("10.000");
			
		} else if(CommonConstants.CREDIT_SCORE_BOUNDRY_SECOND <= creditScoreValue) {
			result = income.multiply(CommonConstants.CREDIT_SCORE_MULTIPLIER);
		}
		
		return result;
	}

	private int compareIncome(BigDecimal income) {
		return income.compareTo(CommonConstants.MONTHLY_INCOME);
	}
}
