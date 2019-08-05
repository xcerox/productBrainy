package com.doit.book.socialmultiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class MultiplicationResultAttempt {

	private final User user;
	private final Multiplication multiplication;
	private final int resultAttempt;

	public MultiplicationResultAttempt() {
		user = null;
		multiplication = null;
		resultAttempt = -1;
	}
}
