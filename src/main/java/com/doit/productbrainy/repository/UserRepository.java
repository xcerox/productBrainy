package com.doit.productbrainy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doit.productbrainy.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByAlias(final String alias);
}
