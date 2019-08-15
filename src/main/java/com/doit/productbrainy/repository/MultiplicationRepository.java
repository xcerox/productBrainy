package com.doit.productbrainy.repository;

import org.springframework.data.repository.CrudRepository;

import com.doit.productbrainy.domain.Multiplication;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {

}
