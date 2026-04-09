package com.klu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.Course;
import com.klu.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repo;

	public Course addCourse(Course course) {
		return repo.save(course);
	}

	public List<Course> getAllCourses() {
		return repo.findAll();
	}

	public Optional<Course> getCourseById(Long id) {
		return repo.findById(id);
	}

	public Course updateCourse(Long id, Course course) {

		Optional<Course> existingCourse = repo.findById(id);

		if (existingCourse.isPresent()) {

			Course c = existingCourse.get();

			c.setTitle(course.getTitle());
			c.setDuration(course.getDuration());
			c.setFee(course.getFee());

			return repo.save(c);
		}

		return null;
	}

	public boolean deleteCourse(Long id) {

		Optional<Course> course = repo.findById(id);

		if (course.isPresent()) {
			repo.deleteById(id);
			return true;
		}

		return false;
	}

	public List<Course> searchByTitle(String title) {
		return repo.findByTitleContainingIgnoreCase(title);
	}

}