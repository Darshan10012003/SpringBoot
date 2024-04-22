package com.serviceadmin.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serviceadmin.entities.FeeDetails;
import com.serviceadmin.objectmapeer.ObjectMappers;
import com.serviceadmin.proxies.FeeDetailsProxy;
import com.serviceadmin.repositories.FeeDetailsRepo;
import com.serviceadmin.services.FeeDetailsServices;
import com.serviceadmin.tokenvalidate.TokenCheck;

@Component
public class FeeDetailsImpl implements FeeDetailsServices{

	@Autowired
	FeeDetailsRepo feeDetailsRepo;
	
	@Autowired
	ObjectMappers objectMapper;
	
	@Autowired
	TokenCheck tokenCheck;

	@Override
	public FeeDetailsProxy getFeeDetails(Long id,Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if(tokencheckisvalidate==true) {
		FeeDetails feeDetails = feeDetailsRepo.findById(id).get();
		FeeDetailsProxy feeProxy = objectMapper.entitytoProxy(feeDetails);
		return feeProxy;
		}
		return null;
	}

	@Override
	public String addFeeDetails(FeeDetailsProxy feeDetailsProxy,Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if(tokencheckisvalidate==true) {
		FeeDetails feeentity = objectMapper.proxytoentity(feeDetailsProxy);
		feeDetailsRepo.save(feeentity);
		return "Saved Successfully";
		}
		return null;
	}

//	@Override
//	public FeeDetailsProxy getFeeDetails(Long id) {
//		// TODO Auto-generated method stub
//		 FeeDetails feeDetails = feeDetailsRepo.findById(id).get();
//		 FeeDetailsProxy feeProxy = objectMapper.entitytoProxy(feeDetails);
//		return feeProxy;
//	}
}
