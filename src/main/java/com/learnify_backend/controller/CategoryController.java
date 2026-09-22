package com.learnify_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.learnify_backend.dto.CategoryDto;
import com.learnify_backend.service.CategoryService;

import lombok.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CategoryController {
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto dto){
        CategoryDto categoryDto = categoryService.createCategory(dto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/showAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }
}
