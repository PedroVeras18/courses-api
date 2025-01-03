package br.com.pedroveras.courses_api.modules.course.useCases;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedroveras.courses_api.modules.course.CourseEntity;
import br.com.pedroveras.courses_api.modules.course.CourseRepository;
import br.com.pedroveras.courses_api.modules.course.CourseStatus;
import br.com.pedroveras.courses_api.modules.course.dto.ListCourseDTO;

@Service
public class ListCoursesUseCase {
    
    @Autowired
    private CourseRepository courseRepository;

    public List<ListCourseDTO> execute(){
        List<CourseEntity> courses = this.courseRepository.findAll();

        return courses.stream()
            .sorted(Comparator.comparing(CourseEntity::getCreatedAt))
            .map(course -> new ListCourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getCategory(),
                course.getActive() == CourseStatus.ACTIVE,
                course.getCreatedAt(),
                course.getUpdatedAt()
            )).collect(Collectors.toList());
    }
}
