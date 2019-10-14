package com.tuyji.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories(basePackages="com.tuyji.repository")
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(
				"mongodb+srv://tuyji:cGHuBCqO1lSEtPxL@cluster0-xiy3j.mongodb.net/test?retryWrites=true&w=majority");
		return new MongoClient(uri);
	}

	@Override
	protected String getDatabaseName() {
		return "test";
	}

}
