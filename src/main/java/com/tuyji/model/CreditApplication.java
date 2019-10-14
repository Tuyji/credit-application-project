package com.tuyji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "creditapplication")
public class CreditApplication {

	@Id
	private @NonNull String identityNumber;
	private @NonNull String name;
	private @NonNull String surname;
	private @NonNull String income;
	private @NonNull String phonenumber;
	private String result;
	private String creditLimit;
	
}
