/**
 * 
 */
package com.starzplay.assignment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starzplay.assignment.response.ResponseObject;
import com.starzplay.assignment.util.Utils;

/**
 * @author Assad.Ul-Hassan
 *
 */
@Controller
@RequestMapping("/verification_code")
public class RestApiController {

	private Map<String, String> storeCode = new HashMap<String, String>();

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject verificationCode(@RequestBody(required = false) String id) {
		ResponseObject responseUtils = new ResponseObject();
		try {
			if (id != null) {
				String generatedString = Utils.generateRandomAlphaNumericString(6);
				if(storeCode.get(id) == null) { 
					storeCode.put(id, generatedString);	
				} else {
					storeCode.replace(id, generatedString);
				}
				responseUtils.setMessage("successfull");
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
	public ResponseObject getVerificationCode(@PathVariable String id, @PathVariable String code) {
		ResponseObject responseUtils = new ResponseObject();
		try {
			if (id != null) {
				if (storeCode.get(id) != null) {
					if(storeCode.get(id).equalsIgnoreCase(code)){
						responseUtils.setMessage(code);
						responseUtils.setValid("true");
					} else {
						responseUtils.setMessage(code);
						responseUtils.setValid("false");
					}
				} else {
					responseUtils.setMessage(code);
					responseUtils.setValid("false");
				}
			} else {
				responseUtils.setMessage("id missing");
				responseUtils.setValid("false");
			}
		} catch (Exception ex) {
			responseUtils.setMessage("error in connecting server : " + ex.getLocalizedMessage());
			responseUtils.setResult_code("0");
		}
		return responseUtils;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "Starz play";
	}

	
}
