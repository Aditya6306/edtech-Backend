package com.learnify_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learnify_backend.entity.SubSection;

public interface SubSectionRepository extends JpaRepository<SubSection, Long> {
    
}
