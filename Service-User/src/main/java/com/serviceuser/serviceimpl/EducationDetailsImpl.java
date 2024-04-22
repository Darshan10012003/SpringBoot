package com.serviceuser.serviceimpl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serviceuser.entities.EducationDetails;
import com.serviceuser.proxies.EducationDetailsProxy;
import com.serviceuser.repositories.EducationDetailsRepo;
import com.serviceuser.service.EducationDetailsServices;
import com.serviceuser.tokencheck.TokenCheck;
import com.serviceuser.utils.ObjectMapper;

@Component
public class EducationDetailsImpl implements EducationDetailsServices {
	@Autowired
	private EducationDetailsRepo educationDetailsRepo;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TokenCheck tokenCheck;

	@Override
	public EducationDetailsProxy GetEducationDetails(Long id) {
		// TODO Auto-generated method stub
		EducationDetails educationDetails = educationDetailsRepo.findById(id).get();
		EducationDetailsProxy educationProxy = objectMapper.entitytoProxy(educationDetails);
		return educationProxy;
	}

	@Override
	public String addEducationDetails(EducationDetailsProxy educationDetailsProxy, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			EducationDetails proxytoentity = objectMapper.proxytoentity(educationDetailsProxy);
			educationDetailsRepo.save(proxytoentity);
			return "Saved Successfully!!";
		}
		return null;
	}

	@Override
	public String updateEducationDetails(Long id, EducationDetailsProxy educationDetailsProxy,
			Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			Optional<EducationDetails> byId = educationDetailsRepo.findById(id);
			if (byId.isPresent()) {
				EducationDetails proxytoentity = objectMapper.proxytoentity(educationDetailsProxy);
				educationDetailsRepo.save(proxytoentity);
			}
			return "Update Successfully";
		}
		return null;
	}

}
