package com.learnify_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learnify_backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
