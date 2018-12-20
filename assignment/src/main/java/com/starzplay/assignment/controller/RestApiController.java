/**
 * 
 */
package com.starzplay.assignment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starzplay.assignment.util.ResponseUtils;

/**
 * @author Assad.Ul-Hassan
 *
 */
@Controller
@RequestMapping("/verification_code")
public class RestApiController {

	private Map<Integer, String> storeCode = new HashMap<Integer, String>();

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseUtils verificationCode(@RequestBody(required = false) String id) {
		ResponseUtils responseUtils = new ResponseUtils();
		try {
			if (id != null) {
				String generatedString = generateRandomString();
				storeCode.put(Integer.valueOf(id), generatedString);
				responseUtils.setMessage("success");
				responseUtils.setResult_code(generatedString);
			} else {
				responseUtils.setMessage("error");
				responseUtils.setResult_code("0");
			}
		} catch (Exception ex) {
			responseUtils.setMessage("error in connecting server : " + ex.getLocalizedMessage());
			responseUtils.setResult_code("0");
		}
		return responseUtils;
	}
	
	@RequestMapping(value = "/{id}/{code}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseUtils getVerificationCode(@PathVariable String id, @PathVariable String code) {
		ResponseUtils responseUtils = new ResponseUtils();
		try {
			if (id != null) {
				if (storeCode.get(Integer.valueOf(id)).equalsIgnoreCase(code)) {
					responseUtils.setMessage("success");
					responseUtils.setResult_code("1");
				} else {
					responseUtils.setMessage("code error");
					responseUtils.setResult_code("0");
				}
			} else {
				responseUtils.setMessage("error");
				responseUtils.setResult_code("0");
			}
		} catch (Exception ex) {
			responseUtils.setMessage("error in connecting server : " + ex.getLocalizedMessage());
			responseUtils.setResult_code("0");
		}
		return responseUtils;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String test(){
		return "Starz play";
	}
	
	public static String generateRandomString() {
		  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    System.out.println(buffer.toString());
	    return buffer.toString();
	}
}
