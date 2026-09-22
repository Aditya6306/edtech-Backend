package com.learnify_backend.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseRequestDto {
    private Long id;
    private String courseName;
    private String courseDescription;
    private String whatYouWillLearn;
    private Long instructorId;
    private Double price;
    private Long categoryId; 
    // private String thumbnail;
    // private String tags;
    private MultipartFile thumbnailImage;
    // private String instructions;
    private String status;
}
