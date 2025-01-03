package br.com.pedroveras.courses_api.modules.course.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedroveras.courses_api.exceptions.CourseFoundException;
import br.com.pedroveras.courses_api.modules.course.CourseEntity;
import br.com.pedroveras.courses_api.modules.course.CourseMapper;
import br.com.pedroveras.courses_api.modules.course.CourseRepository;
import br.com.pedroveras.courses_api.modules.course.dto.CourseIdDTO;
import br.com.pedroveras.courses_api.modules.course.dto.CreateCourseDTO;

@Service
public class CreateCourseUseCase {
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public CourseIdDTO execute(CreateCourseDTO createCourseDTO) {
      CourseEntity courseEntity = courseMapper.toEntity(createCourseDTO);

      this.courseRepository.findByName(courseEntity.getName()).ifPresent(course -> {
        throw new CourseFoundException();
      });

      CourseEntity savedCourse = this.courseRepository.save(courseEntity);

      return new CourseIdDTO(savedCourse.getId());
    }
}
