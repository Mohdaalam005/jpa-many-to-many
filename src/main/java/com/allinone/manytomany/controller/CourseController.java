package com.allinone.manytomany.controller;

import com.allinone.manytomany.model.CourseRequest;
import com.allinone.manytomany.model.CourseResponse;

import com.allinone.manytomany.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService ;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping(path = "/courses",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseRequest>>getAllCourse(){
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
    }
    @GetMapping(path = "/courses/{courseId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseRequest>getCourse(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.getCourse(courseId),HttpStatus.OK);
    }
    @PostMapping(path = "/courses",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponse>createCourse(@RequestBody CourseRequest courseRequest){
        return new ResponseEntity<>(courseService.createCourse(courseRequest),HttpStatus.CREATED);
    }
    @PutMapping(path = "/courses/{courseId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseRequest>updateCourse(@PathVariable Long courseId,@RequestBody CourseRequest courseRequest){
        return new ResponseEntity<>(courseService.updateCourse(courseId,courseRequest),HttpStatus.OK);
    }
    @DeleteMapping(path = "/courses/{courseId}")
    public ResponseEntity<Void>deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
