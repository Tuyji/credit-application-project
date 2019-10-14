package com.tuyji.controller.response;

import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditApplicationResponse {
	
	private String result;
	private String creditLimit;

}
