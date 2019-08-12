package com.doit.book.socialmultiplication.controller.message;

import com.doit.book.socialmultiplication.domain.Multiplication;
import com.doit.book.socialmultiplication.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CheckOperationResultResponse {

	private final User user;
	private final Multiplication multiplication;
	private final Integer product;

	private final boolean correct;
	
	public CheckOperationResultResponse(CheckOperationResultRequest attempt, boolean isCorrect) {
		user = attempt.getUser();
		multiplication = attempt.getMultiplication();
		product = attempt.getProduct();
		correct = isCorrect;
	}
}
