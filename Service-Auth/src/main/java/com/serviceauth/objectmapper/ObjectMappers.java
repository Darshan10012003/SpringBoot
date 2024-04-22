package com.serviceauth.objectmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceauth.entites.AuthEntity;
import com.serviceauth.proxie.AuthProxie;

@Component
public class ObjectMappers {

@Autowired
ObjectMapper objectMapper;
	
	public AuthEntity proxytoentity(AuthProxie authProxie) {
		AuthEntity convertValue = objectMapper.convertValue(authProxie, AuthEntity.class);
		return convertValue;
	}
	
//	public StudentProxy entitytoproxy(Student student) {
//		StudentProxy convertValue = objectMapper.convertValue(student, StudentProxy.class);
//		return convertValue;
//	}
}
