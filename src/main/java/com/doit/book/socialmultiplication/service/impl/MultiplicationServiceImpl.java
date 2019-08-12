package com.doit.book.socialmultiplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doit.book.socialmultiplication.controller.message.CheckOperationResultRequest;
import com.doit.book.socialmultiplication.domain.Multiplication;
import com.doit.book.socialmultiplication.service.MultiplicationService;
import com.doit.book.socialmultiplication.service.RandomNumberService;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	private RandomNumberService randomNumberService;

	@Autowired
	public MultiplicationServiceImpl(RandomNumberService randomNumberService) {
		super();
		this.randomNumberService = randomNumberService;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomNumberService.generateRandomFactor();
		int factorB = randomNumberService.generateRandomFactor();
		return new Multiplication(factorA, factorB);
	}

	@Override
	public boolean checkAttempt(CheckOperationResultRequest attempt) {
		return attempt.getProduct() == (attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB());
	}
}

