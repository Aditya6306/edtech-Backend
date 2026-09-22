package com.learnify_backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnify_backend.entity.Course;
import com.learnify_backend.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByCourse(Course course);
}
