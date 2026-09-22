package com.learnify_backend.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private String description;
}
