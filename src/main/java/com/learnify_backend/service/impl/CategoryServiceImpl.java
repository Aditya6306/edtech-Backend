package com.learnify_backend.service.impl;
import com.learnify_backend.dto.CategoryDto;
import com.learnify_backend.entity.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;


import com.learnify_backend.repository.CategoryRepository;
import com.learnify_backend.repository.CourseRepository;
import com.learnify_backend.service.CategoryService;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        CategoryDto catDto = modelMapper.map(savedCategory, CategoryDto.class);
        return catDto;
    }

    @Override
    public List<CategoryDto> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category cat : categories){
            categoryDtos.add(modelMapper.map(cat, CategoryDto.class));

        }
        return categoryDtos;
    }
}
