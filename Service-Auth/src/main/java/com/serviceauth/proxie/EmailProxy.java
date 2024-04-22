package com.serviceauth.proxie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailProxy {

	private String to;
	private String msgBody;
	private String subject;
	private String attachment;

}
