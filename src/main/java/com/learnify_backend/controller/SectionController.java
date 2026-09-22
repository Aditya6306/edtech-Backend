package com.learnify_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnify_backend.dto.SectionDto;
import com.learnify_backend.dto.CourseResponseDto;
import com.learnify_backend.service.SectionService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/course/section")
@RestController
@AllArgsConstructor
public class SectionController {
    private SectionService sectionService;

    @PostMapping("/addSection")
    public ResponseEntity<CourseResponseDto> createSection(@RequestBody SectionDto sectionDto){
        CourseResponseDto createdSection = sectionService.createSection(sectionDto);
        return new ResponseEntity<>(createdSection, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SectionDto>> getAllSections(@PathVariable Long id){
        return new ResponseEntity<>(sectionService.getAllSections(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SectionDto> updateSection(@PathVariable Long id, @RequestBody SectionDto dto){
        SectionDto sectionDto = sectionService.updateSection(id, dto);
        return new ResponseEntity<>(sectionDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable Long id){
        String deletedSection = sectionService.deleteSection(id);
        return  ResponseEntity.ok(deletedSection);
    }
}
