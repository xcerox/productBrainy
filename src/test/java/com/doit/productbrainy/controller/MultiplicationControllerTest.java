package com.doit.productbrainy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import com.doit.productbrainy.core.WrapperResponse;
import com.doit.productbrainy.domain.Multiplication;
import com.doit.productbrainy.service.MultiplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

	@MockBean
	private MultiplicationService multiplicationService;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<WrapperResponse> json;
	
	@Before
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void getRandomMultiplicationTest() throws Exception {
		// Given
		given(multiplicationService.createRandomMultiplication()).willReturn(new  Multiplication(70,  20));
		WrapperResponse wrapperRenponse = WrapperResponse.builder().successful(true).data(new Multiplication(70, 20)).build();
		
		// When 
		MockHttpServletResponse response = mvc.perform(get("/multiplications/random").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		// Then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(wrapperRenponse).getJson());
		
	}
}
