package com.klu.entity;

import org.springframework.stereotype.Component;

@Component
public class Certification {

	private int id;
	private String name;
	private String dateOfCompletion;

	public Certification() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateOfCompletion(String dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	@Override
	public String toString() {
		return "Certification ID: " + id + "\nCertification Name: " + name + "\nDate Of Completion: "
				+ dateOfCompletion;
	}
}