package com.tuyji.service;

import org.springframework.stereotype.Service;

import com.tuyji.util.CommonConstants;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SMSService {

	public Message sendSMS(String phoneNumber, String status) {
		Twilio.init(CommonConstants.ACCOUNT_SID, CommonConstants.AUTH_TOKEN);
		
		Message message = Message.creator(
				new com.twilio.type.PhoneNumber(phoneNumber),//The phone number you are sending text to
				new com.twilio.type.PhoneNumber("+XXXXXXXXXXX"),//The Twilio phone number
				"Your credit application has been " + status)
				.create();

		return message;
	}
}
