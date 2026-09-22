package com.learnify_backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learnify_backend.dto.SectionDto;
import com.learnify_backend.dto.CourseResponseDto;
import com.learnify_backend.entity.Course;
import com.learnify_backend.entity.Section;
import com.learnify_backend.repository.CourseRepository;
import com.learnify_backend.repository.SectionRepository;
import com.learnify_backend.service.SectionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService{
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper; 

    @Override
    public CourseResponseDto createSection(SectionDto sectionDto){
        Section section = new Section();
        section.setSectionName(sectionDto.getSectionName());
        // Course course = courseRepository.findById(sectionDto.getCourseId());
        Course course = courseRepository.findById(sectionDto.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));
        section.setCourse(course);
        Section savedSection = sectionRepository.save(section);

        CourseResponseDto responseDto = new CourseResponseDto();
        Course savedCourse = courseRepository.findById(sectionDto.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));   

        responseDto.setId(savedCourse.getId());
        responseDto.setCourseName(savedCourse.getCourseName());
        responseDto.setCourseDescription(savedCourse.getCourseDescription());
        responseDto.setPrice(savedCourse.getPrice());
        responseDto.setCategoryName(savedCourse.getCategory().getName());
        responseDto.setStatus(savedCourse.getStatus());
        responseDto.setThumbnail(savedCourse.getThumbnail());
        responseDto.setCourseContent(savedCourse.getSections().stream()
                .map(sec -> modelMapper.map(sec, com.learnify_backend.dto.SectionDto.class))
                .collect(Collectors.toList()));
        return responseDto;
        
    }

    @Override
    public List<SectionDto> getAllSections(Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        List<Section> sections = sectionRepository.findByCourse(course);

        List<SectionDto> sectionDtos = new ArrayList<>();
        for(Section section : sections){
            sectionDtos.add(modelMapper.map(section , SectionDto.class));
        }

        return sectionDtos;
    }

    @Override
    public SectionDto updateSection(Long sectionId, SectionDto sectionDto){
        Section section = sectionRepository.findById(sectionId).orElseThrow(()-> new RuntimeException("Section not found"));
        section.setSectionName(sectionDto.getSectionName());
        return modelMapper.map(section, SectionDto.class);
    }

    @Override
    public String deleteSection(Long id){
        Section existing = sectionRepository.findById(id).orElseThrow(()-> new RuntimeException("Section not found"));
        sectionRepository.delete(existing);
        return ("Section deleted successfully");
    }
}
