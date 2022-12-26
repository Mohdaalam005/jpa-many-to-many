package com.allinone.manytomany.repository;

import com.allinone.manytomany.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEntityRepository extends JpaRepository<CourseEntity, Long> {
}