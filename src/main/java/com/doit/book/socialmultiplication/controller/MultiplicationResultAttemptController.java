package com.doit.book.socialmultiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doit.book.socialmultiplication.controller.message.CheckOperationResultResponse;
import com.doit.book.socialmultiplication.domain.MultiplicationResultAttempt;
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
	CheckOperationResultResponse checkOperationResult(@RequestBody MultiplicationResultAttempt attempt) {
		boolean resultAttempt = multiplicationService.checkAttempt(attempt);
		return new CheckOperationResultResponse(resultAttempt);
	}

}
