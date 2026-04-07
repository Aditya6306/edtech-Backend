package com.learnify_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    @Column(length = 2000)
    private String courseDescription;
    private String whatYouWillLearn;
    private Double price;
    private String thumbNail;
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Section> sections;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<RatingAndReview> ratingsAndReviews;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> studentsEnrolled;

    @ElementCollection
    private List<String> tag;

}
