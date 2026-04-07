package com.learnify_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "course_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    
    @ManyToMany
    @JoinTable(
            name = "course_progress_completed_videos",
            joinColumns = @JoinColumn(name = "course_progress_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_section_id")
    )
    private List<SubSection> completedVideos;
}