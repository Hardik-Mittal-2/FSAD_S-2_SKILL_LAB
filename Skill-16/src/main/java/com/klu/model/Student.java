package com.klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Student Entity")
public class Student {

	@Id
	@Schema(description = "Student ID", example = "1")
	private int id;

	@Schema(description = "Student Name", example = "Hardik Mittal")
	private String name;

	@Schema(description = "Email Address", example = "hardik@gmail.com")
	private String email;

	@Schema(description = "Course Name", example = "FSAD")
	private String course;

	public Student() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}