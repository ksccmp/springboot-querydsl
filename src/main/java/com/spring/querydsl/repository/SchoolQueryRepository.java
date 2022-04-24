package com.spring.querydsl.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.querydsl.entity.QSchool;
import com.spring.querydsl.entity.QStudent;
import com.spring.querydsl.entity.School;


@Repository
public class SchoolQueryRepository {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	/**
	 * 일반 조회
	 */
	@Description("모든 데이터를 조회 (select * from school)")
	public List<School> findAll() {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .fetch();
	}
	
	@Description("조회된 데이터 중 첫 번째 데이터만 조회 (select * from school limit 0, 1)")
	public School findFirst() {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .fetchFirst();
	}
	
	@Description("모든 컬럼을 조회하지 않고 조회하고 싶은 컬럼만 조회 (select no, name from school)")
	public List<School> findNoAndName() {
		return jpaQueryFactory.select(Projections.fields(School.class,
									  QSchool.school.no,
									  QSchool.school.name)
								)
							  .from(QSchool.school)
							  .fetch();
	}
	
	@Description("distinct를 이용한 조회 (select distinct address from school)")
	public List<School> findDistinctAddress() {
		return jpaQueryFactory.select(Projections.fields(School.class,
									  QSchool.school.address)
								)
							  .from(QSchool.school)
							  .distinct()
							  .fetch();
	}
	
	/**
	 * where절 (일치)
	 */
	@Description("데이터 한 개만 조회, 2개 이상 데이터가 조회될 경우 에러 발생 (select * from school where no = #{no})")
	public School findOneByNo(int no) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.eq(no))
							  .fetchOne();
	}
	
	@Description("address값이 일치한 데이터만 조회 (select * from school where address = #{address})")
	public List<School> findByAddress(String address) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.address.eq(address))
							  .fetch();
	}
	
	@Description("name, address값이 일치한 데이터만 조회 (select * from school where name = #{name} and address = #{address})")
	public List<School> findByNameAndAddress(String name, String address) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.name.eq(name), QSchool.school.address.eq(address))
							  .fetch();
	}
	
	@Description("name이 일치하거나 address값이 일치한 데이터만 조회 (select * from school where name = #{name} or address = #{address})")
	public List<School> findByNameOrAddress(String name, String address) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.name.eq(name).or(QSchool.school.address.eq(address)))
							  .fetch();
	}
	
	@Description("address가 일치하지 않은 데이터만 조회 (select * from school where address <> #{address}")
	public List<School> findByNotAddress(String address) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.address.ne(address))
							  .fetch();
	}
	
	@Description("address가 null이 아닌 데이터만 조회 (select * from school where address is not null")
	public List<School> findByAddressNotNull() {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.address.isNotNull())
							  .fetch();
	}
	
	/**
	 * where절 (포함)
	 */
	@Description("특정 no가 포함된 데이터만 조회 (select * from school where no in (#{no1}, #{no2} ... )")
	public List<School> findByInNo(List<Integer> noList) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.in(noList))
							  .fetch();
	}
	
	@Description("특정 no가 포함되지 않은 데이터만 조회 (select * from school where no not in (#{no1}, #{no2} ... )")
	public List<School> findByNotInNo(List<Integer> noList) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.notIn(noList))
							  .fetch();
	}
	
	@Description("address에 특정 값이 포함된 데이터만 조회 (select * from school where address like #{address}")
	public List<School> findByLikeAddress_1(String address) { // %사용은 parameter값에 추가 
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.address.like(address))
							  .fetch();
	}
	
	@Description("address에 특정 값이 포함된 데이터만 조회 (select * from school where address like '%' || #{address} || '%'")
	public List<School> findByLikeAddress_2(String address) { // 양방향 % 자동으로 추가 
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.address.contains(address))
							  .fetch();
	}
	
	/**
	 * where절 (부등호)
	 */
	@Description("no값이 특정 기준 이상인 데이터만 조회 (select * from school where no >= #{no})")
	public List<School> findByGreaterEqualNo(int no) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.goe(no))
							  .fetch();
	}
	
	@Description("no값이 특정 기준 초과인 데이터만 조회 (select * from school where no > #{no})")
	public List<School> findByGreaterNo(int no) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.gt(no))
							  .fetch();
	}
	
	@Description("no값이 특정 기준 이하인 데이터만 조회 (select * from school where no <= #{no})")
	public List<School> findByLessEqualNo(int no) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.loe(no))
							  .fetch();
	}
	
	@Description("no값이 특정 기준 미만인 데이터만 조회 (select * from school where no < #{no})")
	public List<School> findByLessNo(int no) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.lt(no))
							  .fetch();
	}
	
	@Description("no값이 특정 기준 사이인 데이터만 조회 (select * from school where no between #{fromNo} and #{toNo}")
	public List<School> findByBetweenNo(int fromNo, int toNo) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.between(fromNo, toNo))
							  .fetch();
	}	
	
	
	/**
	 * 정렬
	 */
	@Description("조회된 데이터를 no를 기준으로 오름차순 정렬 (select * from school order by no asc)")
	public List<School> findAllOrderByNo() {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .orderBy(QSchool.school.no.asc())
							  .fetch();
	}
	
	@Description("조회된 데이터를 no를 기준으로 내림차순 정렬 (select * from school order by no desc)")
	public List<School> findAllOrderByNoDesc() {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .orderBy(QSchool.school.no.desc())
							  .fetch();
	}
	
	/**
	 * 세타 조인
	 */
	@Description("student와 theta join (select school.* from school, student where school.no = student.school_no)")
	public List<School> thetaJoin() {
		return jpaQueryFactory.select(QSchool.school)
							  .from(QSchool.school, QStudent.student)
							  .where(QSchool.school.no.eq(QStudent.student.schoolNo))
							  .distinct()
							  .fetch();
	}
	
	/**
	 * 이너 조인
	 */
	@Description("student와 inner join (select school.*, student.* from school inner join student on school_no = student.school_no)")
	public List<School> innerJoin_1() { // 연관관계 매핑되어 있을 경우, 한번에 데이터를 조회함 (n+1문제 해결)
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .join(QSchool.school.studentList, QStudent.student)
							  .fetchJoin()
							  .fetch();
	}
	
	@Description("student와 inner join (select school.* from school inner join student on school_no = student.school_no)")
	public List<School> innerJoin_2() { // 연관관계 매핑되어 있을 경우, school을 조회한 뒤 student를 조회함 (n+1문제 발생)
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .join(QSchool.school.studentList, QStudent.student)
							  .fetch();
	}
	
	@Description("student와 inner join (select school.* from school inner join student on school_no = student.school_no)")
	public List<School> innerJoin_3() { // 연관관계 매핑되어 있지 않은 경우
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .join(QStudent.student)
							  .on(QSchool.school.no.eq(QStudent.student.no))
							  .fetch();
	}
	
	/**
	 * 아우터 조인
	 */
	@Description("student와 outer left join (select school.*, student.* from school left outer join student on school_no = student.school_no)")
	public List<School> leftJoin_1() { // 연관관계 매핑되어 있을 경우, 한번에 데이터를 조회함 (n+1문제 해결)
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .leftJoin(QSchool.school.studentList, QStudent.student)
							  .fetchJoin()
							  .fetch();
	}
	
	@Description("student와 outer left join (select school.* from school left outer join student on school_no = student.school_no)")
	public List<School> leftJoin_2() { // 연관관계 매핑되어 있을 경우, school을 조회한 뒤 student를 조회함 (n+1문제 발생)
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .leftJoin(QSchool.school.studentList, QStudent.student)
							  .fetch();
	}
	
	@Description("student와 outer left join (select school.* from school left outer join student on school_no = student.school_no)")
	public List<School> leftJoin_3() { // 연관관계 매핑되어 있지 않은 경우
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .leftJoin(QStudent.student)
							  .on(QSchool.school.no.eq(QStudent.student.schoolNo))
							  .fetch();
	}
	
	/**
	 * 번외 (서브쿼리)
	 */
	@Description("student를 서브쿼리로 사용 (select * from school where no in (select school_no from student where name like '%' || #{studentName} || '%'))")
	public List<School> subQuery(String studentName) {
		return jpaQueryFactory.selectFrom(QSchool.school)
							  .where(QSchool.school.no.in(
								  jpaQueryFactory.selectFrom(QStudent.student)
								  .where(QStudent.student.name.contains(studentName))
								  .fetchAll()
								  .stream()
								  .flatMap(student -> Stream.of(student.getSchoolNo()))
								  .collect(Collectors.toList())
							  ))
							  .fetch();
	}
}
