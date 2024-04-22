package com.serviceadmin.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviceadmin.proxies.AdministartorProxy;
import com.serviceadmin.proxies.StudentProxy;
@Service
public interface AdministartorServices {
	public String registerWithDetails(AdministartorProxy administartorProxy,Map<String, String> headerdata);

	public String updateUserDetails(String enrollmentNo, StudentProxy studentProxy,Map<String, String> headerdata);

	public String updateAdminDetails(Long id, AdministartorProxy administartorProxy,Map<String, String> headerdata);

	public String deleteUser(String enrollmentNo,Map<String, String> headerdata);

	public List<StudentProxy> getUsers(Map<String, String> headerdata);

	public StudentProxy getUser(String enrollmentNo,Map<String, String> headerdata);

	public AdministartorProxy getAdmin(Long id,Map<String, String> headerdata);
}
