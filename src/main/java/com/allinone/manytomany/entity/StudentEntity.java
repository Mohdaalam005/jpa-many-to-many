package com.allinone.manytomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "studentId", referencedColumnName = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    )
    @JsonIgnore
    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    private List<CourseEntity> courses;

}
