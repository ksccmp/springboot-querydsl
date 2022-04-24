package com.spring.querydsl.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "school")
public class School {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int no;
	private String name;
	private String address;
	
	@OneToMany
	@JoinColumn(name = "schoolNo", referencedColumnName = "no")
	private List<Student> studentList;
}
