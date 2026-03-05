package com.klu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.exception.StudentNotFoundException;
import com.klu.model.Student;
import com.klu.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository repo;

	public Student addStudent(Student s) {
		return repo.save(s);
	}

	public Student getStudent(int id) {

		Optional<Student> student = repo.findById(id);

		if (student.isPresent())
			return student.get();
		else
			throw new StudentNotFoundException("Student ID " + id + " not found");

	}
}