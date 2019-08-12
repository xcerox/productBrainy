package com.doit.book.socialmultiplication.service;

import com.doit.book.socialmultiplication.controller.message.CheckOperationResultRequest;
import com.doit.book.socialmultiplication.domain.Multiplication;

public interface MultiplicationService {
	
	Multiplication createRandomMultiplication();
	
	boolean checkAttempt(final CheckOperationResultRequest resultAttempt);
}
