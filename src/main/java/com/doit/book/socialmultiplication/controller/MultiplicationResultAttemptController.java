package com.doit.book.socialmultiplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doit.book.socialmultiplication.controller.message.CheckOperationResultRequest;
import com.doit.book.socialmultiplication.controller.message.CheckOperationResultResponse;
import com.doit.book.socialmultiplication.service.MultiplicationService;

@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {
	
	private final MultiplicationService multiplicationService;
	
	@Autowired
	public MultiplicationResultAttemptController(final MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}
	
	@PostMapping
	CheckOperationResultResponse checkOperationResult(@Valid @RequestBody CheckOperationResultRequest attempt) {
		boolean isCorrect = multiplicationService.checkAttempt(attempt);
		return new CheckOperationResultResponse(attempt, isCorrect);
	}
}
