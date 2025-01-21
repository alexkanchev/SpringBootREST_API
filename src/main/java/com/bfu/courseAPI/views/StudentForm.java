package com.bfu.courseAPI.views;

import java.util.List;

public class StudentForm {
	
	private Long id;
	private String name;
	private String email;
	private List<Long> clasIds;
	
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
	public List<Long> getClasIds() {
		return clasIds;
	}
	public void setClasIds(List<Long> clasIds) {
		this.clasIds = clasIds;
	}

}
