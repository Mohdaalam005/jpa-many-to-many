package com.allinone.manytomany.model;

import com.allinone.manytomany.entity.CourseEntity;
import lombok.*;
import java.util.List;

/**
 * A DTO for the {@link com.allinone.manytomany.entity.StudentEntity} entity
 */
@Getter
@Setter
@ToString
public class StudentRequest extends StudentResponse {
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private List<CourseEntity> courses;
}