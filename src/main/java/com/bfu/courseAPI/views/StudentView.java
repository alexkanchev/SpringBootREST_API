package com.bfu.courseAPI.views;

import java.util.List;

public class StudentView {

	private Long id;
	private String name;
	private String email;
	private List<String> grades;
	private List<String> clasNames;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getGrades() {
		return grades;
	}
	public void setGrades(List<String> grades) {
		this.grades = grades;
	}
	public List<String> getClasNames() {
		return clasNames;
	}
	public void setClasNames(List<String> clasNames) {
		this.clasNames = clasNames;
	}
	
	
}
