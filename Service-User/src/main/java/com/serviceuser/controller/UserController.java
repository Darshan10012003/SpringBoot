package com.serviceuser.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceuser.proxies.EducationDetailsProxy;
import com.serviceuser.proxies.StudentProxy;
import com.serviceuser.service.EducationDetailsServices;
import com.serviceuser.service.StudentServices;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private StudentServices studentServices;
	
	
//	@Autowired
//	private FeeDetailsServices feeDetailsServices;
	
	@Autowired
	private EducationDetailsServices educationDetailsServices;

	@PostMapping("/register")
	public ResponseEntity<String> registerWithDetails(@RequestBody StudentProxy studentProxy,@RequestHeader Map<String, String> headerdata) {
		System.out.println("data"+studentProxy);
		return new ResponseEntity<String>(studentServices.registerWithDetails(studentProxy, headerdata), HttpStatus.ACCEPTED);
	}

	@PostMapping("/updateUser/{enrollmentNo}")
	public ResponseEntity<String> updateUserDetails(@PathVariable String enrollmentNo,
			@RequestBody StudentProxy studentProxy) {
		return new ResponseEntity<String>(studentServices.updateUserDetails(enrollmentNo, studentProxy),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/getUser/{enrollmentNo}")
	public ResponseEntity<StudentProxy> getUser(@PathVariable String enrollmentNo) {
		return new ResponseEntity<StudentProxy>(studentServices.getUser(enrollmentNo), HttpStatus.OK);
	}

//	@GetMapping("getFeeDetails/{id}")
//	public ResponseEntity<FeeDetailsProxy> getFeeDetails(@PathVariable Long id) {
//		return new ResponseEntity<FeeDetailsProxy>(feeDetailsServices.getFeeDetails(id), HttpStatus.OK);
//	}

	@PostMapping("/addEducationDetails")
	public ResponseEntity<String> addEducationDetails(@RequestBody EducationDetailsProxy educationDetailsProxy,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<String>(educationDetailsServices.addEducationDetails(educationDetailsProxy, headerdata), HttpStatus.OK);
	}

	@PostMapping("/updateEducationDetails/{id}")
	public ResponseEntity<String> updateEducationDetails(@PathVariable Long id,@RequestBody EducationDetailsProxy educationDetailsProxy,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<String>(educationDetailsServices.updateEducationDetails(id, educationDetailsProxy, headerdata), HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<StudentProxy>> getAllUser(){
		return new ResponseEntity<List<StudentProxy>>(studentServices.getAllUser(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllUserU/{pagenumber}/{pagesize}")
	public ResponseEntity<List<StudentProxy>> getAllUserU(@PathVariable Integer pagenumber,@PathVariable Integer pagesize,@RequestHeader Map<String, String> headerdata){
		return new ResponseEntity<List<StudentProxy>>(studentServices.getAllUserU(pagenumber, pagesize,headerdata),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/updateUserU/{enrollmentNo}")
	public ResponseEntity<String> updateUserDetailsU(@PathVariable String enrollmentNo,
			@RequestBody StudentProxy studentProxy,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<String>(studentServices.updateUserDetailsU(enrollmentNo, studentProxy, headerdata),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/getUserU/{enrollmentNo}")
	public ResponseEntity<StudentProxy> getUserU(@PathVariable String enrollmentNo,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<StudentProxy>(studentServices.getUserU(enrollmentNo, headerdata), HttpStatus.OK);
	}
	
	@GetMapping("/savedata1234")
	public String savedata() {
		return studentServices.savedata();
	}

}
