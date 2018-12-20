/**
 * 
 */
package com.starzplay.assignment.util;

import org.springframework.stereotype.Component;

/**
 * @author Assad.Ul-Hassan
 *
 */
@Component
public class ResponseUtils {

	private String result_code;
	
	private String message;

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
