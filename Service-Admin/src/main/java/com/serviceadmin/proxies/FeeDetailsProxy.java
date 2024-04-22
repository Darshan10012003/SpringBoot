package com.serviceadmin.proxies;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeDetailsProxy {
	private Long id;
	private String student_enrollmentNo;
	private Double feeAmount;
	private Date dueDate;
	private String updateby;
	private Date updateDate;
}
