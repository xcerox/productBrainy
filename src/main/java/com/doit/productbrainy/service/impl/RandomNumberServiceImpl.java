package com.doit.productbrainy.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.doit.productbrainy.service.RandomNumberService;

@Service
public class RandomNumberServiceImpl implements RandomNumberService {
	
	final static int FACTOR_MIN = 11;
	final static int FACTOR_MAX = 99;
	
	@Override
	public int generateRandomFactor() {
		return new Random().nextInt((FACTOR_MAX - FACTOR_MIN) + 1) + FACTOR_MIN;
	}

}
