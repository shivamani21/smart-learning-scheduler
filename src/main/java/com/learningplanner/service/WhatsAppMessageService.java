package com.learningplanner.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppMessageService {

	@Value("${whatsapp.api.url:}")
	private String apiUrl;

	@Value("${whatsapp.phone.number.id:}")
	private String phoneNumberId;

	@Value("${whatsapp.access.token:}")
	private String accessToken;


	public void sendWhatsAppMessage(String to, String message) {

	    if (apiUrl.isEmpty() || phoneNumberId.isEmpty() || accessToken.isEmpty()) {
	        System.out.println("WhatsApp not configured. Skipping message send.");
	        return;
	    }

	    try {
	        String url = apiUrl + "/" + phoneNumberId + "/messages";

	        RestTemplate restTemplate = new RestTemplate();

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(accessToken);

	        Map<String, Object> body = new HashMap<>();
	        body.put("messaging_product", "whatsapp");
	        body.put("to", to);
	        body.put("type", "text");

	        Map<String, String> textObj = new HashMap<>();
	        textObj.put("body", message);
	        body.put("text", textObj);

	        HttpEntity<Map<String, Object>> request =
	                new HttpEntity<>(body, headers);

	        restTemplate.postForEntity(url, request, String.class);

	        System.out.println("WhatsApp message sent to: " + to);

	    } catch (Exception e) {
	        System.out.println("Failed to send WhatsApp message: " + e.getMessage());
	    }
	}

}
