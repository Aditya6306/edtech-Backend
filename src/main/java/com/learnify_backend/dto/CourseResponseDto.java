package com.learnify_backend.dto;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseResponseDto {
    private Long id;
    private String courseName;
    private String courseDescription;
    
    private Double price;

    private String categoryName;

    private List<SectionDto> courseContent;
    private String status;
    private String thumbnail;
}
