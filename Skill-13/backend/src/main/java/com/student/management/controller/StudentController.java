package com.student.management.controller;

import com.student.management.model.Student;
import com.student.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Student Controller", description = "Student management APIs")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET /students → Retrieve all students
    @Operation(summary = "Retrieve all students", description = "Fetch a list of all students in the system")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of students")
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // GET /students/{id} → Retrieve a student by ID
    @Operation(summary = "Retrieve a student by ID", description = "Fetch details of a specific student by their unique ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST /students → Add a new student
    @Operation(summary = "Add a new student", description = "Create a new student record")
    @ApiResponse(responseCode = "201", description = "Student successfully created")
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // PUT /students/{id} → Update an existing student
    @Operation(summary = "Update an existing student", description = "Modify the details of an existing student by ID")
    @ApiResponse(responseCode = "200", description = "Student successfully updated")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /students/{id} → Delete a student by ID
    @Operation(summary = "Delete a student", description = "Remove a student record from the system by ID")
    @ApiResponse(responseCode = "204", description = "Student successfully deleted")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
