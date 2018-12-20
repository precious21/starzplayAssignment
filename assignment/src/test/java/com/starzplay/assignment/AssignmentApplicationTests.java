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
	public void testVerificationCode_withId_shouldReturnResponseUtils() {
		ResponseObject responseUtils =  mockController.verificationCode("444");
		Assertions.assertThat(responseUtils).isNotNull();
		Assertions.assertThat(responseUtils.getMessage()).isEqualTo("successfull");
	}
	
	@Test
	public void testVerificationCode_withNullId_shouldReturnResponseUtils() {
		ResponseObject responseUtils =  mockController.verificationCode(null);
		Assertions.assertThat(responseUtils).isNotNull();
		Assertions.assertThat(responseUtils.getMessage()).isEqualTo("error");
	}
}

