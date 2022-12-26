package com.allinone.manytomany.model;

import com.allinone.manytomany.entity.StudentEntity;
import lombok.*;

import java.util.List;

/**
 * A DTO for the {@link com.allinone.manytomany.entity.CourseEntity} entity
 */

@Getter
@Setter
@ToString
public class CourseRequest extends CourseResponse {
    private String courseName;
    private String coursePrice;
    private String courseDuration;
    private List<StudentEntity>students ;
}