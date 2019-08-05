package com.doit.book.socialmultiplication.service;

import com.doit.book.socialmultiplication.domain.Multiplication;
import com.doit.book.socialmultiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
	
	Multiplication createRandomMultiplication();
	
	boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
}
