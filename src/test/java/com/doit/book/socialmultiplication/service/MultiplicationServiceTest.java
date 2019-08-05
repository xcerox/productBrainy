package com.doit.book.socialmultiplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.doit.book.socialmultiplication.domain.Multiplication;
import com.doit.book.socialmultiplication.domain.MultiplicationResultAttempt;
import com.doit.book.socialmultiplication.domain.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {

	//	mvnw -Dtest=MultiplicationServiceTest test
	// mvnw clean compile test
	
	@MockBean
	private RandomNumberService randomNumberService;
	
	@Autowired
	private MultiplicationService multiplicationService;
	
	@Test
	public void checkCorrectAttemptTest() {
		//Given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("Frank");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000);
		
		//When
		boolean attemptResult = multiplicationService.checkAttempt(attempt);
		
		//Assert
		assertThat(attemptResult).isTrue();
	}
	
	@Test
	public void checkWrongCorrectAttemptTest() {
		//Given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("Frank");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010);
		
		//When
		boolean attemptResult = multiplicationService.checkAttempt(attempt);
		
		//Assert
		assertThat(attemptResult).isFalse();
	}
}
