package com.allinone.manytomany.service;

import com.allinone.manytomany.entity.CourseEntity;
import com.allinone.manytomany.mapper.StudentCourseMapper;
import com.allinone.manytomany.model.CourseRequest;
import com.allinone.manytomany.model.CourseResponse;
import com.allinone.manytomany.repository.CourseEntityRepository;
import com.allinone.manytomany.repository.StudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final StudentCourseMapper studentCourseMapper;
    private final StudentEntityRepository studentEntityRepository;
    private final CourseEntityRepository courseEntityRepository;

    @Autowired
    public CourseService(StudentCourseMapper studentCourseMapper, StudentEntityRepository studentEntityRepository, CourseEntityRepository courseEntityRepository) {
        this.studentCourseMapper = studentCourseMapper;
        this.studentEntityRepository = studentEntityRepository;
        this.courseEntityRepository = courseEntityRepository;
    }
    public List<CourseRequest>getAllCourse(){
        List<CourseEntity> courses = courseEntityRepository.findAll();
        return  studentCourseMapper.courseEntityToCourseRequest(courses);
    }
    public CourseRequest getCourse(Long courseId){
        CourseRequest courseRequest = null ;
        Optional<CourseEntity> courseById = courseEntityRepository.findById(courseId);
        if (courseById.isPresent()){
            courseRequest = studentCourseMapper.courseEntityToCourseRequest(courseById.get());
        }
        return courseRequest ;
    }
    public CourseResponse createCourse(CourseRequest courseRequest){
        CourseEntity courseEntity = studentCourseMapper.courseRequestToCourseEntity(courseRequest);
        CourseEntity save = courseEntityRepository.save(courseEntity);
       CourseResponse  courseResponse = new CourseResponse();
        courseResponse.setCourseId(save.getCourseId());
        return courseResponse;
    }
    public CourseRequest updateCourse(Long courseId,CourseRequest courseRequest){
        CourseRequest courseRequest1 = null ;
        Optional<CourseEntity> courseById = courseEntityRepository.findById(courseId);
        if (courseById.isPresent()){
            CourseEntity courseEntity = studentCourseMapper.courseRequestToCourseEntity(courseRequest);
            courseEntity.setCourseId(courseId);
            CourseEntity save = courseEntityRepository.save(courseEntity);
             courseRequest1 = studentCourseMapper.courseEntityToCourseRequest(save);
        }
        return courseRequest1;
    }
    public void deleteCourse(Long courseId){
        Optional<CourseEntity> courseById = courseEntityRepository.findById(courseId);
        if (courseById.isPresent()){
            courseEntityRepository.deleteById(courseId);
        }
    }

}
