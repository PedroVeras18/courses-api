package br.com.pedroveras.courses_api.modules.course.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedroveras.courses_api.modules.course.dto.CreateCourseDTO;
import br.com.pedroveras.courses_api.modules.course.dto.UpdateCourseDTO;
import br.com.pedroveras.courses_api.modules.course.useCases.CreateCourseUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.ListCoursesUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.UpdateCourseUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CouseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private ListCoursesUseCase listCoursesUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        try {
            var result = createCourseUseCase.execute(createCourseDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
        try {
            this.updateCourseUseCase.execute(id, updateCourseDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}