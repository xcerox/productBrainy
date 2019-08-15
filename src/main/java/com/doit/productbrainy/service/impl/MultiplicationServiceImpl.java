package com.doit.productbrainy.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doit.productbrainy.controller.message.CheckOperationResultRequest;
import com.doit.productbrainy.domain.Multiplication;
import com.doit.productbrainy.domain.MultiplicationResultAttempt;
import com.doit.productbrainy.domain.User;
import com.doit.productbrainy.repository.MultiplicationResultAttemptRepository;
import com.doit.productbrainy.repository.UserRepository;
import com.doit.productbrainy.service.MultiplicationService;
import com.doit.productbrainy.service.RandomNumberService;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	private RandomNumberService randomNumberService;
	private MultiplicationResultAttemptRepository attemptRepository;
	private UserRepository userRepository;

	@Autowired
	public MultiplicationServiceImpl(final RandomNumberService randomNumberService,
			MultiplicationResultAttemptRepository attemptRepository, UserRepository userRepository) {
		super();
		this.randomNumberService = randomNumberService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomNumberService.generateRandomFactor();
		int factorB = randomNumberService.generateRandomFactor();
		return new Multiplication(factorA, factorB);
	}

	@Override
	@Transactional
	public boolean checkAttempt(CheckOperationResultRequest attempt) {

		Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());
		int product = attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();
		boolean isCorrect = product == attempt.getProduct();

		MultiplicationResultAttempt checkAttempt = new MultiplicationResultAttempt(user.orElse(attempt.getUser()),
				attempt.getMultiplication(), attempt.getProduct(), isCorrect);
		attemptRepository.save(checkAttempt);

		return isCorrect;
	}

	@Override
	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
		return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	}
}
