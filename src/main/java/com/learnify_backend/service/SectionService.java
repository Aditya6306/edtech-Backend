package com.learnify_backend.service;

import java.util.List;

import com.learnify_backend.dto.SectionDto;
import com.learnify_backend.dto.CourseResponseDto;

public interface SectionService {
    public CourseResponseDto createSection(SectionDto sectionDto);
    public List<SectionDto> getAllSections(Long courseId);
    public SectionDto updateSection(Long sectionId, SectionDto sectionDto);
    public String deleteSection(Long id);
}