package com.spring.querydsl.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class StudentQueryRepository {
	
	@Autowired
	private JPAQueryFactory jpaQueryFacotry;
}
