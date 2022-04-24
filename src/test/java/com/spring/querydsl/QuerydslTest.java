package com.spring.querydsl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.querydsl.repository.SchoolQueryRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
class QuerydslTest {
	
	@Autowired
	SchoolQueryRepository schoolQueryRepository;

	@Test
	void normal() {
		log.info(schoolQueryRepository.findAll().toString());
		log.info(schoolQueryRepository.findFirst().toString());
		log.info(schoolQueryRepository.findNoAndName().toString());
		log.info(schoolQueryRepository.findDistinctAddress().toString());
	}
	
	@Test
	void whereEqual() {
		log.info(schoolQueryRepository.findOneByNo(3).toString());
		log.info(schoolQueryRepository.findByAddress("Seoul").toString());
		log.info(schoolQueryRepository.findByNameAndAddress("A School","Seoul").toString());
		log.info(schoolQueryRepository.findByNameOrAddress("E School","Seoul").toString());
		log.info(schoolQueryRepository.findByNotAddress("Seoul").toString());
		log.info(schoolQueryRepository.findByAddressNotNull().toString());
	}
	
	@Test
	void whereInclude() {
		List<Integer> noList = new ArrayList<>();
		noList.add(2);
		noList.add(3);
		
		log.info(schoolQueryRepository.findByInNo(noList).toString());
		log.info(schoolQueryRepository.findByNotInNo(noList).toString());
		log.info(schoolQueryRepository.findByLikeAddress_1("%s%").toString());
		log.info(schoolQueryRepository.findByLikeAddress_2("s").toString());
	}
	
	@Test
	void whereThen() {
		log.info(schoolQueryRepository.findByGreaterEqualNo(3).toString());
		log.info(schoolQueryRepository.findByGreaterNo(3).toString());
		log.info(schoolQueryRepository.findByLessEqualNo(3).toString());
		log.info(schoolQueryRepository.findByLessNo(3).toString());
		log.info(schoolQueryRepository.findByBetweenNo(2, 4).toString());
	}
	
	@Test
	void orderBy() {
		log.info(schoolQueryRepository.findAllOrderByNo().toString());
		log.info(schoolQueryRepository.findAllOrderByNoDesc().toString());
	}
	
	@Test
	void thetaJoin() {
		log.info(schoolQueryRepository.thetaJoin().toString());
	}
	
	@Test
	void innerJoin() {
		log.info(schoolQueryRepository.innerJoin_1().toString());
		log.info(schoolQueryRepository.innerJoin_2().toString());
		log.info(schoolQueryRepository.innerJoin_3().toString());
	}
	
	@Test
	void outerJoin() {
		log.info(schoolQueryRepository.leftJoin_1().toString());
		log.info(schoolQueryRepository.leftJoin_2().toString());
		log.info(schoolQueryRepository.leftJoin_3().toString());
	}
	
	@Test
	void extra() {
		log.info(schoolQueryRepository.subQuery("1").toString());
	}
}
