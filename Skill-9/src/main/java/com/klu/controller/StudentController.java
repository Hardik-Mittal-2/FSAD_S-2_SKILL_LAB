package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.exception.InvalidInputException;
import com.klu.model.Student;
import com.klu.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/addstudent")
    public Student addStudent(@RequestBody Student s) {
        return service.addStudent(s);
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id) {

        if(id <= 0)
            throw new InvalidInputException("Invalid student ID");

        return service.getStudent(id);
    }
}