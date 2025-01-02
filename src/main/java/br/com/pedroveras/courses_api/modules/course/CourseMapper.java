package br.com.pedroveras.courses_api.modules.course;

import org.springframework.stereotype.Component;

import br.com.pedroveras.courses_api.modules.course.dto.CreateCourseDTO;

@Component
public class CourseMapper {
    public CourseEntity toEntity(CreateCourseDTO dto) {
        return CourseEntity.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .category(dto.getCategory())
            .active(dto.getActive() != null && dto.getActive() ? CourseStatus.ACTIVE : CourseStatus.INACTIVE)
            .build();
    }
}