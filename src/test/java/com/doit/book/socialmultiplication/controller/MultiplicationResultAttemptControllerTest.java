package com.doit.book.socialmultiplication.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.doit.book.socialmultiplication.controller.message.CheckOperationResultRequest;
import com.doit.book.socialmultiplication.controller.message.CheckOperationResultResponse;
import com.doit.book.socialmultiplication.domain.Multiplication;
import com.doit.book.socialmultiplication.domain.User;
import com.doit.book.socialmultiplication.service.MultiplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {
	
	@MockBean
	private MultiplicationService multiplicationService;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<CheckOperationResultRequest> jsonRequest;
	private JacksonTester<CheckOperationResultResponse> jsonResponse;
	
	@Before
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void postResultReturnCorrect() throws Exception {
		Multiplication operation = new Multiplication(50, 70);
		Integer product = 3500;
		generateParamerizedTest(operation, product, true);
	}
	
	@Test
	public void postResultReturnNotCorrect() throws Exception {
		Multiplication operation = new Multiplication(50, 70);
		Integer product = 0;
		generateParamerizedTest(operation, product, true);
	}
	
	void generateParamerizedTest(Multiplication operation, Integer product, boolean isCorrect) throws Exception {
		// given
		given(multiplicationService.checkAttempt(any(CheckOperationResultRequest.class))).willReturn(isCorrect);
		User user = new User("john");
		CheckOperationResultRequest attempt = new CheckOperationResultRequest(user, operation, product);
		RequestBuilder request = post("/results").contentType(MediaType.APPLICATION_JSON).content(jsonRequest.write(attempt).getJson());
		
		// when
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new CheckOperationResultResponse(attempt, isCorrect)).getJson());
		
	}
	
	
}
