package br.com.pedroveras.courses_api.modules.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedroveras.courses_api.modules.course.dto.CreateCourseDTO;
import br.com.pedroveras.courses_api.modules.course.useCases.CreateCourseUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CouseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        try {
            var result = createCourseUseCase.execute(createCourseDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}