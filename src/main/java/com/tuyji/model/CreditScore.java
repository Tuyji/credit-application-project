package com.tuyji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Document(collection = "creditscore")
public class CreditScore {

	@Id
	private @NonNull String identityNumber;
	private @NonNull String creditScore;
	
}
