package com.allinone.manytomany.service;

import com.allinone.manytomany.entity.CourseEntity;
import com.allinone.manytomany.entity.StudentEntity;
import com.allinone.manytomany.mapper.StudentCourseMapper;
import com.allinone.manytomany.model.CourseRequest;
import com.allinone.manytomany.model.StudentRequest;
import com.allinone.manytomany.model.StudentResponse;
import com.allinone.manytomany.repository.CourseEntityRepository;
import com.allinone.manytomany.repository.StudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentCourseMapper studentCourseMapper;
    private final StudentEntityRepository studentEntityRepository;
    private final CourseEntityRepository courseEntityRepository;

    @Autowired
    public StudentService(StudentCourseMapper studentCourseMapper, StudentEntityRepository studentEntityRepository, CourseEntityRepository courseEntityRepository) {
        this.studentCourseMapper = studentCourseMapper;
        this.studentEntityRepository = studentEntityRepository;
        this.courseEntityRepository = courseEntityRepository;
    }

    public StudentResponse createStudent(StudentRequest studentRequest) {
        StudentEntity studentEntity = studentCourseMapper.studentRequestToStudentEntity(studentRequest);
        StudentEntity save = studentEntityRepository.save(studentEntity);
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(save.getStudentId());
        return studentResponse ;
    }
    public StudentRequest getStudent(Long studentId){
        StudentEntity entity = null ;
        Optional<StudentEntity> studentEntity = studentEntityRepository.findById(studentId);
        if (studentEntity.isPresent()){
           entity  = studentEntity.get();
        }
        return studentCourseMapper.studentEntityToStudentRequest(entity);
    }
    public List<StudentRequest> getAllStudent(){
        List<StudentEntity> students = studentEntityRepository.findAll();
        return studentCourseMapper.studentEntityToStudentRequests(students);
    }
    public StudentRequest updateStudent(Long studentId,StudentRequest studentRequest){
        StudentRequest studentRequest1 = null ;
        Optional<StudentEntity> studentById = studentEntityRepository.findById(studentId);
        if (studentById.isPresent()){
            StudentEntity studentEntity = studentCourseMapper.studentRequestToStudentEntity(studentRequest);
            studentEntity.setStudentId(studentId);
            StudentEntity save = studentEntityRepository.save(studentEntity);
           studentRequest1 =  studentCourseMapper.studentEntityToStudentRequest(save);
        }
        return studentRequest1 ;
    }
    public void deleteStudent(Long studentId){
        Optional<StudentEntity> studentById = studentEntityRepository.findById(studentId);
        if (studentById.isPresent()){
            studentEntityRepository.deleteById(studentId);
        }
    }
    public void assign(Long studentId,Long courseId) {
        List<CourseEntity> courses = null;
        Optional<StudentEntity> studentById = studentEntityRepository.findById(studentId);
        Optional<CourseEntity> courseById = courseEntityRepository.findById(courseId);
       courses = studentById.get().getCourses() ;
       courses.add(courseById.get());
       studentById.get().setCourses(courses);
     studentEntityRepository.save(studentById.get());
    }


}
