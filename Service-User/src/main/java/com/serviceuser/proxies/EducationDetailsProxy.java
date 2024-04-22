package com.serviceuser.proxies;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDetailsProxy {
	private Long id;
	private String student_enrollmentNo;
	private String educationType;
	private String percentage;
	private boolean qualified;
	private String updatedBy;
	private Date updatedDate;
}
