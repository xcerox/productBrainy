package com.doit.productbrainy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
public final class Multiplication {

	@Id
	@GeneratedValue
	@Column(name = "MULTIPLICATION_ID")
	private Long id;
	
	private final int factorA;
	private final int factorB;

	Multiplication() {
		factorA = 0;
		factorB = 0;
	}

}
