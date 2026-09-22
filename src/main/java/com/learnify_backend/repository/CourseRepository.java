package com.learnify_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learnify_backend.entity.Course;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // @Query("SELECT c FROM Course c WHERE c.category.id = :categoryId")
    List<Course> findByCategory_Id(Long categoryId);
    List<Course> findByInstructor_Id(Long instructorId);
}
