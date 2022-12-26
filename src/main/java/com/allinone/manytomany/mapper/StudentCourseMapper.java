package com.allinone.manytomany.mapper;

import com.allinone.manytomany.entity.CourseEntity;
import com.allinone.manytomany.entity.StudentEntity;
import com.allinone.manytomany.model.CourseRequest;
import com.allinone.manytomany.model.StudentRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {

    StudentEntity studentRequestToStudentEntity(StudentRequest studentRequest);

    StudentRequest studentEntityToStudentRequest(StudentEntity studentEntity );

    List<StudentRequest> studentEntityToStudentRequests(List<StudentEntity> studentEntity );

    CourseRequest courseEntityToCourseRequest(CourseEntity courseEntity);

    List<CourseRequest> courseEntityToCourseRequest(List<CourseEntity> courseEntity);


    CourseEntity courseRequestToCourseEntity(CourseRequest courseRequest);

}
