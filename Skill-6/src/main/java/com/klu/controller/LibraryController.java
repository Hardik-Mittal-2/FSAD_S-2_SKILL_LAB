package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Book;
import com.klu.service.BookService;

@RestController
public class LibraryController {

	@Autowired
	BookService s1;

	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book b1) {
		return s1.addBook(b1);
	}

	@GetMapping("/viewBooks")
	public List<Book> viewBooks() {
		return s1.viewBooks();
	}
}