package com.serviceuser.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.serviceuser.proxies.StudentProxy;

@Service
public interface StudentServices {

	public String registerWithDetails(StudentProxy studentProxy,Map<String, String> headerdata);

	public String updateUserDetails(String enrollmentNo, StudentProxy studentProxy);

	public StudentProxy getUser(String enrollmentNo);

	public List<StudentProxy> getAllUser();

	public String updateUserDetailsU(String enrollmentNo, StudentProxy studentProxy,Map<String, String> headerdata);

	public StudentProxy getUserU(String enrollmentNo,Map<String, String> headerdata);

	public List<StudentProxy> getAllUserU(Integer pagenumber,Integer pagesize,Map<String, String> headerdata);
	
	public String savedata();
}
