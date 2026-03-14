package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.entity.Student;
import com.klu.service.StudentService;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getStudents(){
        return service.getAllStudents();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        service.deleteStudent(id);
    }
}