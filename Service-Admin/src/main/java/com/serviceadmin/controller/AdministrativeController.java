package com.serviceadmin.controller;

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

import com.serviceadmin.proxies.AdministartorProxy;
import com.serviceadmin.proxies.FeeDetailsProxy;
import com.serviceadmin.proxies.StudentProxy;
import com.serviceadmin.services.AdministartorServices;
import com.serviceadmin.services.FeeDetailsServices;

@RestController
@RequestMapping("/admin")
public class AdministrativeController {
	@Autowired
	private AdministartorServices administartorServices;
	
	@Autowired
	private FeeDetailsServices feeDetailsServices;
	
//	@Autowired
//	private EducationDetailsServices educationDetailsServices;

	@PostMapping("/register")
	public ResponseEntity<String> registerWithDetails(@RequestBody AdministartorProxy administartorProxy,@RequestHeader Map<String, String> headerdata) {
		System.out.println("dataaa "+administartorProxy);
		return new ResponseEntity<String>(administartorServices.registerWithDetails(administartorProxy, headerdata), HttpStatus.ACCEPTED);
	}

	@PostMapping("/updateUser/{enrollmentNo}")
	public ResponseEntity<String> updateUserDetails(@PathVariable String enrollmentNo,@RequestBody StudentProxy studentProxy,@RequestHeader Map<String, String> headerdata) {
		//String postForObject = restTemplate.postForObject("http://localhost:8092/user/updateUser/"+enrollmentNo,studentProxy, String.class);
		return new ResponseEntity<String>(administartorServices.updateUserDetails(enrollmentNo, studentProxy, headerdata), HttpStatus.ACCEPTED);
	}
	@PostMapping("/updateAdmin/{id}")
	public ResponseEntity<String> updateAdminDetails(@PathVariable Long id,@RequestBody AdministartorProxy administartorProxy,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<String>(administartorServices.updateAdminDetails(id, administartorProxy, headerdata), HttpStatus.ACCEPTED);
	}

	@PostMapping("/deleteUser/{enrollmentNo}")
	public ResponseEntity<String> deleteUser(@PathVariable String enrollmentNo,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<String>(administartorServices.deleteUser(enrollmentNo, headerdata), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getAllUser/{pagenumber}/{pagesize}")
	public ResponseEntity<List<StudentProxy>> getUsers(@RequestHeader Map<String, String> headerdata){
		//ArrayList<StudentProxy> forObject = restTemplate.getForObject("http://localhost:8092/user/getAllUser", ArrayList.class);
		return new ResponseEntity<List<StudentProxy>>(administartorServices.getUsers(headerdata), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getUser/{enrollmentNo}")
	public ResponseEntity<StudentProxy> getUser(@RequestHeader Map<String, String> headerdata,@PathVariable String enrollmentNo) {
		System.out.println("data"+enrollmentNo+headerdata);
	//StudentProxy forObject = restTemplate.getForObject("http://localhost:8092/user/getUser/"+enrollmentNo, StudentProxy.class);
		return new ResponseEntity<StudentProxy>(administartorServices.getUser(enrollmentNo, headerdata), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getAdmin/{id}")
	public ResponseEntity<AdministartorProxy> getAdmin(@PathVariable Long id,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<AdministartorProxy>(administartorServices.getAdmin(id, headerdata), HttpStatus.OK);
	}

	@PostMapping("/addFeeDetails")
	public ResponseEntity<String> addFeeDetails(@RequestBody FeeDetailsProxy feeDetailsProxy,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<String>(feeDetailsServices.addFeeDetails(feeDetailsProxy, headerdata), HttpStatus.OK);
	}

	@GetMapping("/getFeeDetails/{id}")
	public ResponseEntity<FeeDetailsProxy> getFeeDetails(@PathVariable Long id,@RequestHeader Map<String, String> headerdata) {
		return new ResponseEntity<FeeDetailsProxy>(feeDetailsServices.getFeeDetails(id, headerdata), HttpStatus.OK);
	}

//	@GetMapping("getEducationDetails/{id}")
//	public ResponseEntity<EducationDetailsProxy> GetEducationDetails(@PathVariable Long id) {
//		return new ResponseEntity<EducationDetailsProxy>(educationDetailsServices.GetEducationDetails(id), HttpStatus.OK);
//	}
}
