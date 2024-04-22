package com.serviceuser.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serviceuser.entities.EducationDetails;
import com.serviceuser.entities.Student;
import com.serviceuser.proxies.EducationDetailsProxy;
import com.serviceuser.proxies.StudentProxy;
@Component
public class ObjectMapper {
	@Autowired
	com.fasterxml.jackson.databind.ObjectMapper objectMapper;
	
	public Student proxytoentity(StudentProxy studentProxy) {
		Student convertValue = objectMapper.convertValue(studentProxy, Student.class);
		return convertValue;
	}
	
	public StudentProxy entitytoproxy(Student student) {
		StudentProxy convertValue = objectMapper.convertValue(student, StudentProxy.class);
		return convertValue;
	}
	public EducationDetails proxytoentity(EducationDetailsProxy educationDetailsProxy) {
		EducationDetails convertValue = objectMapper.convertValue(educationDetailsProxy, EducationDetails.class);
		return convertValue;
	}
	
	public EducationDetailsProxy entitytoProxy(EducationDetails educationDetails) {
		EducationDetailsProxy convertValue = objectMapper.convertValue(educationDetails, EducationDetailsProxy.class);
		return convertValue;
	}
}
