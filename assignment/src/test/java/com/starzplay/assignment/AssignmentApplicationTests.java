package com.starzplay.assignment;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.starzplay.assignment.controller.RestApiController;
import com.starzplay.assignment.response.ResponseObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {


	public RestApiController mockController;
	
	@Before
	public void setup() {
		mockController = new RestApiController();
	}
	
	@Test
	public void testVerificationCode_withId_shouldReturnresponse() {
		ResponseObject response =  mockController.verificationCode("444");
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(response.getMessage()).isEqualTo("successfull");
	}
	
	@Test
	public void testVerificationCode_withNullId_shouldReturnresponse() {
		ResponseObject response =  mockController.verificationCode(null);
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(response.getMessage()).isEqualTo("error");
	}
	
	@Test
	public void getVerificationCode_withIdAndCode_shouldReturnresponse() {
		ResponseObject response =  mockController.getVerificationCode("444", "abc4fg");
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(response.getMessage()).isEqualTo("abc4fg");
	}
	
	@Test
	public void getVerificationCode_withNullIdAndCode_shouldReturnresponse() {
		ResponseObject response =  mockController.getVerificationCode(null, null);
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(response.getValid()).isEqualTo("false");
	}
}

