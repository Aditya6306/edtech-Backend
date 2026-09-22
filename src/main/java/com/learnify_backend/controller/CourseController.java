package com.learnify_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.learnify_backend.dto.CourseRequestDto;
import com.learnify_backend.dto.CourseResponseDto;
import com.learnify_backend.entity.Course;
import com.learnify_backend.service.CourseService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
    private CourseService courseService;

    

    @PostMapping("/createCourse")
    public ResponseEntity<CourseResponseDto> createCourse(@ModelAttribute CourseRequestDto courseRequestDto) {
        CourseResponseDto createdCourse = courseService.createCourse(courseRequestDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);

        
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        CourseResponseDto course = courseService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("/editCourse")
    public ResponseEntity<CourseResponseDto> editCourse(@ModelAttribute CourseRequestDto courseRequestDto) {
        CourseResponseDto updatedCourse = courseService.editCourse(courseRequestDto);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        String response = courseService.deleteCourse(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByCategoryId(@PathVariable Long categoryId) {
        List<CourseResponseDto> courses = courseService.getCoursesByCategory(categoryId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/getInstructorCourses")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByInstructorId() {
        List<CourseResponseDto> courses = courseService.getCoursesByInstructor();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
