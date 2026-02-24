package com.klu.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

	private int id;
	private String name;
	private String gender;

	@Autowired
	private Certification certification;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Certification getCertification() {
		return certification;
	}

	public void display() {
		System.out.println("\n--- Student Details ---");
		System.out.println("Student ID: " + id);
		System.out.println("Student Name: " + name);
		System.out.println("Gender: " + gender);
		System.out.println("\n--- Certification Details ---");
		System.out.println(certification);
	}
}