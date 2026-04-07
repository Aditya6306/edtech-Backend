package com.learnify_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rating_and_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingAndReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ⭐ Rating
    @Column(nullable = false)
    private int rating;

    // 📝 Review
    @Column(nullable = false, length = 2000)
    private String review;

    // 👤 User (Many reviews by one user)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 📚 Course (Many reviews per course)
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}