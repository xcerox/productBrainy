package com.doit.productbrainy.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.doit.productbrainy.service.RandomNumberService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RandomNumberServiceTest {

//	@Autowired
	private RandomNumberService randomNumberService;

//	@Test
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
