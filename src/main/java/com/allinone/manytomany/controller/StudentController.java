package com.allinone.manytomany.controller;

import com.allinone.manytomany.model.StudentRequest;
import com.allinone.manytomany.model.StudentResponse;
import com.allinone.manytomany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService ;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping(path = "/students",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentRequest>> getAllStudent(){
        return new ResponseEntity<>( studentService.getAllStudent(), HttpStatus.OK);
    }
    @GetMapping(path = "/students/{studentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentRequest>getStudent(@PathVariable Long studentId){
        return new ResponseEntity<>(studentService.getStudent(studentId),HttpStatus.OK);
    }
    @PostMapping(path = "/students",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse>createStudent(@RequestBody StudentRequest studentRequest){
        return new ResponseEntity<>(studentService.createStudent(studentRequest),HttpStatus.CREATED);
    }
    @PutMapping(path = "/students/{studentId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentRequest>updateStudent(@RequestBody StudentRequest studentRequest , @PathVariable Long studentId){
        return new ResponseEntity<>(studentService.updateStudent(studentId,studentRequest),HttpStatus.OK);
    }
    @DeleteMapping(path = "/students/{studentId}")
    public ResponseEntity<Void>deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
    @PutMapping(path = "/students/{studentId}/course/{courseId}")
    public ResponseEntity<Void>assignment(@PathVariable Long studentId,@PathVariable  Long courseId){
        studentService.assign(studentId,courseId) ;
        return  ResponseEntity.ok().build();
    }
}

