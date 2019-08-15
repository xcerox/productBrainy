package com.doit.productbrainy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.assertj.core.util.Lists;
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

import com.doit.productbrainy.controller.message.CheckOperationResultRequest;
import com.doit.productbrainy.controller.message.CheckOperationResultResponse;
import com.doit.productbrainy.domain.Multiplication;
import com.doit.productbrainy.domain.MultiplicationResultAttempt;
import com.doit.productbrainy.domain.User;
import com.doit.productbrainy.service.MultiplicationService;
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
	
	private JacksonTester<List<MultiplicationResultAttempt>> jsonResponseAttempts;
	
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
		MultiplicationResultAttempt attemptResultMock = new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getProduct(), isCorrect);
		
		// when
		RequestBuilder request = post("/results").contentType(MediaType.APPLICATION_JSON).content(jsonRequest.write(attempt).getJson());
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new CheckOperationResultResponse(attemptResultMock)).getJson());
	}
	
	
	@Test
	public void getUserStats() throws Exception {
		//Given 
		User user = new User("frank");
		Multiplication multiplication = new Multiplication(50, 70);
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, true);
		List<MultiplicationResultAttempt> attempts = Lists.newArrayList(attempt, attempt);
		given(multiplicationService.getStatsForUser("frank")).willReturn(attempts);
		
		//When
		RequestBuilder request = get("/results").param("alias", "frank");
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		//Then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonResponseAttempts.write(attempts).getJson());
	}
	
}
