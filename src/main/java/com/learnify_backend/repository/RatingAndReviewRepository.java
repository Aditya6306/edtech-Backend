package com.learnify_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learnify_backend.entity.RatingAndReview; 

public interface RatingAndReviewRepository extends JpaRepository<RatingAndReview, Long> {
    
}
