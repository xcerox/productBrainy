package com.doit.productbrainy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doit.productbrainy.domain.Multiplication;
import com.doit.productbrainy.service.MultiplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {

	private final MultiplicationService multiplicationService;

	@Autowired
	public MultiplicationController(final MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}

	@GetMapping("/random")
	@Operation(summary = "generate two randoms numbers" )
	@ApiResponse(responseCode = "200", description = "successful operation",  content = @Content( schema = @Schema(implementation = Multiplication.BodyResponse.class)))
	Multiplication getRandomMultiplication() {
		return multiplicationService.createRandomMultiplication();
	}
}
