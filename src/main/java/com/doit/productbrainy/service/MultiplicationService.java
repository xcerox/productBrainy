package com.doit.productbrainy.service;

import java.util.List;

import com.doit.productbrainy.controller.message.CheckOperationResultRequest;
import com.doit.productbrainy.domain.Multiplication;
import com.doit.productbrainy.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
	
	Multiplication createRandomMultiplication();
	
	boolean checkAttempt(final CheckOperationResultRequest resultAttempt);
	List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}
