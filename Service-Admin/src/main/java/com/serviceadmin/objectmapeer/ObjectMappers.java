package com.serviceadmin.objectmapeer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceadmin.entities.Administartor;
import com.serviceadmin.entities.FeeDetails;
import com.serviceadmin.proxies.AdministartorProxy;
import com.serviceadmin.proxies.FeeDetailsProxy;
@Component
public class ObjectMappers {
	
	@Autowired
	ObjectMapper objectMapper;
	
	public Administartor proxytoentity(AdministartorProxy administartorProxy) {
		Administartor convertValue = objectMapper.convertValue(administartorProxy, Administartor.class);
		return convertValue;
	}
	
	public AdministartorProxy entitytoProxy(Administartor administartor) {
		AdministartorProxy convertValue = objectMapper.convertValue(administartor, AdministartorProxy.class);
		return convertValue;
	}
	
	public FeeDetails proxytoentity(FeeDetailsProxy feeDetailsProxy) {
		FeeDetails convertValue = objectMapper.convertValue(feeDetailsProxy, FeeDetails.class);
		return convertValue;
	}
	
	public FeeDetailsProxy entitytoProxy(FeeDetails feeDetails) {
		FeeDetailsProxy convertValue = objectMapper.convertValue(feeDetails, FeeDetailsProxy.class);
		return convertValue;
	}
}
