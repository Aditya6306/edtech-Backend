package com.learnify_backend.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionDto {
    private Long id;
    private String sectionName;
    private Long courseId;
}
