package br.com.pedroveras.courses_api.modules.course.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedroveras.courses_api.modules.course.dto.CreateCourseDTO;
import br.com.pedroveras.courses_api.modules.course.dto.UpdateCourseDTO;
import br.com.pedroveras.courses_api.modules.course.useCases.CreateCourseUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.DeleteCourseUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.ListCoursesUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.ToggleCourseActiveStatusUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.UpdateCourseUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private ListCoursesUseCase listCoursesUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private ToggleCourseActiveStatusUseCase toggleCourseActiveStatusUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        var result = createCourseUseCase.execute(createCourseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAll(HttpServletRequest request){
        var result = this.listCoursesUseCase.execute();
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateCourseDTO updateCourseDTO
    ) {
        this.updateCourseUseCase.execute(id, updateCourseDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> toggleActiveCourse(@PathVariable UUID id) {
        this.toggleCourseActiveStatusUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(
        @PathVariable UUID id
    ) {
        this.deleteCourseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}