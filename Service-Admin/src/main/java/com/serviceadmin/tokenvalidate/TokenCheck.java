package com.serviceadmin.tokenvalidate;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class TokenCheck {
	@Autowired
	RestTemplate restTemplate;

	public Boolean tokencheckisvalidate(Map<String, String> headerdata) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization",headerdata.get("token"));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> httpEntity = new HttpEntity<>(null,httpHeaders);
		Boolean postForObject = restTemplate.postForObject("http://AUTH-SERVICE/Auth/auth/validate",httpEntity,Boolean.class);
		return postForObject;
	}
}
