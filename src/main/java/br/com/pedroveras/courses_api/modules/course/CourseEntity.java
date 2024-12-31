package br.com.pedroveras.courses_api.modules.course;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "A descrição é obrigatória")
    @Length(min = 10, max = 100, message = "A descrição deve conter entre (10) e (200) caracteres")
    private String description;

    private String category;

    private CourseStatus active;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
