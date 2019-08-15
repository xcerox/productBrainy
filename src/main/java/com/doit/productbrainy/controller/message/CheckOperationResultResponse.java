package com.doit.productbrainy.controller.message;

import com.doit.productbrainy.domain.MultiplicationResultAttempt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CheckOperationResultResponse {

	private final MultiplicationResultAttempt attempt;
	
	public CheckOperationResultResponse(MultiplicationResultAttempt attempt) {
		this.attempt = attempt;
	}
}
