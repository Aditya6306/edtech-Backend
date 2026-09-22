package com.learnify_backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.learnify_backend.dto.CourseRequestDto;
import com.learnify_backend.dto.CourseResponseDto;
import com.learnify_backend.entity.Course;



public interface CourseService {
    CourseResponseDto createCourse(CourseRequestDto course);
    
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto getCourseById(Long id);
    CourseResponseDto editCourse(CourseRequestDto course);
    String deleteCourse(Long id);
    List<CourseResponseDto> getCoursesByCategory(Long categoryId);
    List<CourseResponseDto> getCoursesByInstructor();
}
