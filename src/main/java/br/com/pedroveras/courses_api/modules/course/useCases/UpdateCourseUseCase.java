package br.com.pedroveras.courses_api.modules.course.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedroveras.courses_api.exceptions.CourseNotFoundException;
import br.com.pedroveras.courses_api.modules.course.CourseEntity;
import br.com.pedroveras.courses_api.modules.course.CourseRepository;
import br.com.pedroveras.courses_api.modules.course.dto.UpdateCourseDTO;

@Service
public class UpdateCourseUseCase {
    
    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID courseId, UpdateCourseDTO updateCourseDTO) {
        CourseEntity course = courseRepository.findById(courseId)
            .orElseThrow(CourseNotFoundException::new);

        if (updateCourseDTO.getName() != null) {
            course.setName(updateCourseDTO.getName());
        }

        if (updateCourseDTO.getDescription() != null) {
            course.setDescription(updateCourseDTO.getDescription());
        }

        if (updateCourseDTO.getCategory() != null) {
            course.setCategory(updateCourseDTO.getCategory());
        }

        this.courseRepository.save(course);
    }
}
