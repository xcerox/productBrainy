package com.doit.book.socialmultiplication.controller.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class CheckOperationResultResponse {
	private final boolean correct;
}
