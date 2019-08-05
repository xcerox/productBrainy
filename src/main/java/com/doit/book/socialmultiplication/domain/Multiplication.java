package com.doit.book.socialmultiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class Multiplication {

	private final int factorA;
	private final int factorB;

	Multiplication() {
		factorA = 0;
		factorB = 0;
	}

}
