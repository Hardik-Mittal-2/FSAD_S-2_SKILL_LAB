package com.klu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Student;
import com.klu.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequestMapping("/students")
@Tag(name = "Student API", description = "CRUD operations for Student")
public class StudentController {

	@Autowired
	private StudentService service;

	@Operation(summary = "Add a new student")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Student Created"),
			@ApiResponse(responseCode = "400", description = "Invalid Input") })
	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student s) {
		return new ResponseEntity<>(service.addStudent(s), HttpStatus.CREATED);
	}

	@Operation(summary = "Get all students")
	@GetMapping
	public List<Student> getAll() {
		return service.getAllStudents();
	}

	@Operation(summary = "Get student by ID")
	@ApiResponse(responseCode = "404", description = "Student Not Found")
	@GetMapping("/{id}")
	public ResponseEntity<?> getStudent(@PathVariable int id) {

		Optional<Student> s = service.getStudent(id);

		if (s.isPresent())
			return ResponseEntity.ok(s.get());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
	}

	@Operation(summary = "Update student")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Student s) {

		Student updated = service.updateStudent(id, s);

		if (updated != null)
			return ResponseEntity.ok(updated);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
	}

	@Operation(summary = "Delete student")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {

		boolean deleted = service.deleteStudent(id);

		if (deleted)
			return ResponseEntity.ok("Deleted Successfully");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
	}
}