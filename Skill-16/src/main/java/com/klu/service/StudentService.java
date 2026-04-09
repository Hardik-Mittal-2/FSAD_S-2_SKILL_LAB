package com.klu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Student;
import com.klu.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	public Student addStudent(Student s) {
		return repo.save(s);
	}

	public List<Student> getAllStudents() {
		return repo.findAll();
	}

	public Optional<Student> getStudent(int id) {
		return repo.findById(id);
	}

	public Student updateStudent(int id, Student s) {

		Optional<Student> existing = repo.findById(id);

		if (existing.isPresent()) {
			Student st = existing.get();
			st.setName(s.getName());
			st.setEmail(s.getEmail());
			st.setCourse(s.getCourse());
			return repo.save(st);
		}

		return null;
	}

	public boolean deleteStudent(int id) {

		Optional<Student> st = repo.findById(id);

		if (st.isPresent()) {
			repo.deleteById(id);
			return true;
		}

		return false;
	}
}