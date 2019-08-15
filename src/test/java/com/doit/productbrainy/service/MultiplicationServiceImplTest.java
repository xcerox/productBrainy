package com.doit.productbrainy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doit.productbrainy.controller.message.CheckOperationResultRequest;
import com.doit.productbrainy.domain.Multiplication;
import com.doit.productbrainy.domain.MultiplicationResultAttempt;
import com.doit.productbrainy.domain.User;
import com.doit.productbrainy.repository.MultiplicationResultAttemptRepository;
import com.doit.productbrainy.repository.UserRepository;
import com.doit.productbrainy.service.impl.MultiplicationServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceImplTest {
	
	private MultiplicationServiceImpl multiplicationServiceImpl;
	
	@Mock
	private RandomNumberService randomNumberService;
	
	@Mock
	private MultiplicationResultAttemptRepository attemptRespository;
	
	@Mock
	private UserRepository userRepository;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		multiplicationServiceImpl = new MultiplicationServiceImpl(randomNumberService, attemptRespository, userRepository);
	}
	
	
	@Test
	public void checkCorrectAttemptTest() {
		//Given
		Multiplication multiplication = new Multiplication(50, 60);
		Integer product = 3000;
		User user = new User("Frank");
		CheckOperationResultRequest attempt = new CheckOperationResultRequest(user, multiplication, product);
		given(userRepository.findByAlias("Frank")).willReturn(Optional.empty());
		MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, product, true);
		
		//When
		boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
		
		//Assert
		assertThat(attemptResult).isTrue();
		verify(attemptRespository).save(verifiedAttempt);
	}
	
	@Test
	public void checkWrongCorrectAttemptTest() {
		//Given
		Multiplication multiplication = new Multiplication(50, 60);
		Integer product = 3010;
		User user = new User("Frank");
		CheckOperationResultRequest attempt = new CheckOperationResultRequest(user, multiplication, product);
		given(userRepository.findByAlias("Frank")).willReturn(Optional.empty());
		MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, product, false);
		
		//When
		boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
		
		//Assert
		assertThat(attemptResult).isFalse();
		verify(attemptRespository).save(verifiedAttempt);
	}
	
	@Test
	public void retrieveStatsTest() {
		//Given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john");
		MultiplicationResultAttempt attempt_1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		MultiplicationResultAttempt attempt_2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);
		List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt_1, attempt_2);
		given(userRepository.findByAlias("john")).willReturn(Optional.empty());
		given(attemptRespository.findTop5ByUserAliasOrderByIdDesc("john")).willReturn(latestAttempts);
		
		//When
		List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationServiceImpl.getStatsForUser("john");
		
		//Then
		assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
	}
}
