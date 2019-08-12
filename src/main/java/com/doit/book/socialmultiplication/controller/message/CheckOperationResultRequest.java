package com.doit.book.socialmultiplication.controller.message;

import javax.validation.constraints.NotNull;

import com.doit.book.socialmultiplication.domain.Multiplication;
import com.doit.book.socialmultiplication.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public final class CheckOperationResultRequest {

	@NotNull(message = "{checkOperationResultRequest.user.notNull}")
	private final User user;
	@NotNull(message = "{checkOperationResultRequest.multiplication.notNull}")
	private final Multiplication multiplication;
	@NotNull(message = "{checkOperationResultRequest.product.notNull}")
	private final Integer product;
}
