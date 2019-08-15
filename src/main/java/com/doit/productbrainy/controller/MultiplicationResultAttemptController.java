package com.doit.productbrainy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doit.productbrainy.controller.message.CheckOperationResultRequest;
import com.doit.productbrainy.controller.message.CheckOperationResultResponse;
import com.doit.productbrainy.domain.MultiplicationResultAttempt;
import com.doit.productbrainy.service.MultiplicationService;

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
		return new CheckOperationResultResponse(new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getProduct(), isCorrect));
	}
	
	@GetMapping
	List<MultiplicationResultAttempt> getStatistics(@RequestParam("alias") String alias) {
		return multiplicationService.getStatsForUser(alias);
	}
}
