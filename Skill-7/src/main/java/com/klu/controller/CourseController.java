package com.klu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.klu.entity.Course;
import com.klu.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService service;

	@PostMapping
	public ResponseEntity<?> addCourse(@RequestBody Course course) {

		Course savedCourse = service.addCourse(course);

		return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {

		return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCourse(@PathVariable Long id) {

		Optional<Course> course = service.getCourseById(id);

		if (course.isPresent()) {
			return ResponseEntity.ok(course.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found");
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {

		Course updatedCourse = service.updateCourse(id, course);

		if (updatedCourse != null) {
			return ResponseEntity.ok(updatedCourse);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

		boolean deleted = service.deleteCourse(id);

		if (deleted) {
			return ResponseEntity.ok("Course Deleted Successfully");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found");
	}

	@GetMapping("/search/{title}")
	public ResponseEntity<List<Course>> searchCourse(@PathVariable String title) {

		return ResponseEntity.ok(service.searchByTitle(title));
	}

}