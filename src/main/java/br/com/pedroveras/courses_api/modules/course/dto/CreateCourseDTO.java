package br.com.pedroveras.courses_api.modules.course.dto;

import lombok.Data;

@Data
public class CreateCourseDTO {
    private String name;
    private String description;
    private String category;
    private Boolean active;
}
