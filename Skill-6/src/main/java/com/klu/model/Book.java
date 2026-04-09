package com.klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	private int id;

	private String title;
	private String author;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int a1) {
		this.id = a1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String a2) {
		this.title = a2;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String a3) {
		this.author = a3;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double a4) {
		this.price = a4;
	}
}