package com.learnify_backend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learnify_backend.dto.CourseRequestDto;
import com.learnify_backend.dto.CourseResponseDto;
import com.learnify_backend.entity.Course;
import com.learnify_backend.entity.User;
import com.learnify_backend.entity.Category;
import com.learnify_backend.repository.CategoryRepository;
import com.learnify_backend.repository.UserRepository;
import com.learnify_backend.service.CourseService;
import com.learnify_backend.service.UploadToCloudinary;
import org.springframework.security.core.Authentication;
import ch.qos.logback.core.model.Model;

import com.learnify_backend.repository.CourseRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;
// import java.lang.String;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final UploadToCloudinary uploadToCloudinary;

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        MultipartFile file = courseRequestDto.getThumbnailImage();

        String imageUrl = uploadToCloudinary.uploadFile(file);
        
        // Convert CourseRequestDto to Course entity
        Course course = new Course();
        course.setThumbnail(imageUrl);
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseDescription(courseRequestDto.getCourseDescription());
        course.setWhatYouWillLearn(courseRequestDto.getWhatYouWillLearn());
        course.setPrice(courseRequestDto.getPrice());
        course.setStatus(courseRequestDto.getStatus());

        Authentication authentication =
    SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User instructor = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        course.setInstructor(instructor);

        Category category = categoryRepository.findById(courseRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        course.setCategory(category);
        // Handle thumbnail upload and set thumbnail URL in course entity
        
        // ArrayList<String> tags = new ArrayList<>();
        // if (courseRequestDto.getTags() != null && !courseRequestDto.getTags().isEmpty()) {
        //     String[] tagsArray = courseRequestDto.getTags().split(",");
        //     for (String tag : tagsArray) {
        //         tags.add(tag.trim());
        //     }
        // }
        // course.setTag(tags);
        // Save the course entity to the database (this is just a placeholder, you need
        // to implement the actual save logic)
        Course savedCourse = courseRepository.save(course);

        // Convert saved Course entity to CourseResponseDto
        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(savedCourse.getId());
        responseDto.setCourseName(savedCourse.getCourseName());
        responseDto.setCourseDescription(savedCourse.getCourseDescription());
        responseDto.setPrice(savedCourse.getPrice());
        responseDto.setCategoryName(savedCourse.getCategory().getName());
        responseDto.setStatus(savedCourse.getStatus());
        responseDto.setThumbnail(savedCourse.getThumbnail());
        // responseDto.setCourseContent(savedCourse.getSections().stream()
        //         .map(section -> modelMapper.map(section, com.learnify_backend.dto.SectionDto.class))
        //         .collect(Collectors.toList()));
        return responseDto;
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        return modelMapper.map(course, CourseResponseDto.class);
    }

    @Override
    public CourseResponseDto editCourse(CourseRequestDto courseRequestDto) {

        System.out.println("Course ID:--------------------- -------------------------------------------------------------------------------------------------" + courseRequestDto.getId());
        Course course = courseRepository.findById(courseRequestDto.getId()).orElseThrow(() -> new RuntimeException("Course not found"));
        // course.setCourseName(courseRequestDto.getCourseName());
        // course.setCourseDescription(courseRequestDto.getCourseDescription());
        // course.setWhatYouWillLearn(courseRequestDto.getWhatYouWillLearn());
        // course.setPrice(courseRequestDto.getPrice());
        course.setStatus(courseRequestDto.getStatus());

        // User instructor = userRepository.findById(courseRequestDto.getInstructorId())
        //         .orElseThrow(() -> new RuntimeException("Instructor not found"));
        // course.setInstructor(instructor);

        // Category category = categoryRepository.findById(courseRequestDto.getCategoryId())
        //         .orElseThrow(() -> new RuntimeException("Category not found"));
        // course.setCategory(category);
        // Handle thumbnail upload and set thumbnail URL in course entity
        // course.setThumbnail(courseRequestDto.getThumbnailImage()); // This is just a placeholder, you need to implement the
                                                              // actual upload logic

        Course updatedCourse = courseRepository.save(course);
        return modelMapper.map(updatedCourse, CourseResponseDto.class);
    }

    @Override
    public String deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
        return "Course with ID " + id + " has been deleted successfully.";
    }

    @Override
    public List<CourseResponseDto> getCoursesByCategory(Long categoryId) {
        List<Course> courses = courseRepository.findByCategory_Id(categoryId);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDto> getCoursesByInstructor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        long id = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Instructor not found"))
                .getId();
        List<Course> courses = courseRepository.findByInstructor_Id(id);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }
}
