package com.tuyji.CreditApplicationProject;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tuyji.controller.response.CreditApplicationResponse;
import com.tuyji.model.CreditApplication;

public class CreditApplicationControllerTest extends AbstractTest {
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addCreditApplication() throws Exception {
		String uri = "/creditapplication";
		CreditApplication app = new CreditApplication();
		app.setIdentityNumber("34180285810");
		app.setName("Volkan");
		app.setSurname("Tuyji");
		app.setPhonenumber("905052603458");
		app.setIncome("3500");
		String inputJson = super.mapToJson(app);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		CreditApplicationResponse response = super.mapFromJson(content, CreditApplicationResponse.class);
		assertEquals(response.getResult(), "ACCEPTED");
	}
}
