package com.doit.book.socialmultiplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.doit.book.socialmultiplication.service.impl.RandomNumberServiceImpl;


public class RandomNumberServiceImplTest {

	private RandomNumberService randomNumberService;

	@Before
	public void setUp() {
		randomNumberService = new RandomNumberServiceImpl();
	}
	
	@Test
	public void generateRandomFactorIsBetween11And100() {
		// when
		List<Integer> randomFactors = IntStream
											.range(0, 1000)
											.map(element -> randomNumberService.generateRandomFactor())
											.boxed().collect(Collectors.toList());
		
		// then
		assertThat(randomFactors).containsOnlyElementsOf(IntStream
																.range(11, 100)
																.boxed()
																.collect(Collectors.toList())
														);
		
		
		

	}

}
