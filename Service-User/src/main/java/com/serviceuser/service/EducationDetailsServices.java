package com.serviceuser.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.serviceuser.proxies.EducationDetailsProxy;
@Service
public interface EducationDetailsServices {
	public EducationDetailsProxy GetEducationDetails(Long id);

	public String addEducationDetails(EducationDetailsProxy educationDetailsProxy,Map<String, String> headerdata);

	public String updateEducationDetails(Long id, EducationDetailsProxy educationDetailsProxy,Map<String, String> headerdata);
}
