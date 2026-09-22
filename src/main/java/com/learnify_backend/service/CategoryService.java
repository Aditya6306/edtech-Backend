package com.learnify_backend.service;
import java.util.List;

import com.learnify_backend.dto.CategoryDto;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto dto);
    public List<CategoryDto> getAllCategory();
}
