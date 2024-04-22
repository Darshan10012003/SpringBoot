package com.serviceadmin.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.serviceadmin.proxies.FeeDetailsProxy;
@Service
public interface FeeDetailsServices {
public String addFeeDetails(FeeDetailsProxy feeDetailsProxy,Map<String, String> headerdata);
	
	public FeeDetailsProxy getFeeDetails(Long id,Map<String, String> headerdata);
}
