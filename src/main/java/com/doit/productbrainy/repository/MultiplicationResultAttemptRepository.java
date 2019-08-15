package com.doit.productbrainy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.doit.productbrainy.domain.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {
	List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
