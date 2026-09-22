package com.learnify_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learnify_backend.entity.CourseProgress;

public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {
    
}
