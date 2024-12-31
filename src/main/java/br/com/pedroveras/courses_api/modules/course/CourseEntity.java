package br.com.pedroveras.courses_api.modules.course;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CourseEntity {
    private UUID id;
    
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "A descrição é obrigatória")
    @Length(min = 10, max = 100, message = "A descrição deve conter entre (10) e (200) caracteres")
    private String description;

    private String category;
    
    private CourseStatus active;
}
