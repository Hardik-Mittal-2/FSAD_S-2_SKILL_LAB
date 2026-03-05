package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Book;
import com.klu.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository r1;

	public Book addBook(Book b1) {
		return r1.save(b1);
	}

	public List<Book> viewBooks() {
		return r1.findAll();
	}

}