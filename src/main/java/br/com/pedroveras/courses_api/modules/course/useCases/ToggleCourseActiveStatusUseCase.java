package br.com.pedroveras.courses_api.modules.course.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedroveras.courses_api.exceptions.CourseNotFoundException;
import br.com.pedroveras.courses_api.modules.course.CourseEntity;
import br.com.pedroveras.courses_api.modules.course.CourseRepository;

@Service
public class ToggleCourseActiveStatusUseCase {
    
    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID courseId) {
        CourseEntity course = courseRepository.findById(courseId)
            .orElseThrow(CourseNotFoundException::new);

        course.setActive(course.getActive().toggle());

        this.courseRepository.save(course);
    }
}