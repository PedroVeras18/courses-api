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

import br.com.pedroveras.courses_api.modules.course.CourseEntity;
import br.com.pedroveras.courses_api.modules.course.dto.CreateCourseDTO;
import br.com.pedroveras.courses_api.modules.course.dto.UpdateCourseDTO;
import br.com.pedroveras.courses_api.modules.course.useCases.CreateCourseUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.DeleteCourseUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.ListCoursesUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.ToggleCourseActiveStatusUseCase;
import br.com.pedroveras.courses_api.modules.course.useCases.UpdateCourseUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
@Tag(name = "Courses", description = "Informações dos cursos")
@ApiResponses({
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = CourseEntity.class))
    })
})
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
    @Operation(summary = "Cadastro de curso", description = "Essa função é responsável por cadastrar um curso")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        var result = createCourseUseCase.execute(createCourseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/list")
    @Operation(summary = "Listagem de cursos", description = "Essa função é responsável por listar os cursos")
    public ResponseEntity<Object> listAll(HttpServletRequest request){
        var result = this.listCoursesUseCase.execute();
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar curso", description = "Essa função é responsável por editar um curso")
    public ResponseEntity<Object> updateCourse(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateCourseDTO updateCourseDTO
    ) {
        this.updateCourseUseCase.execute(id, updateCourseDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    @Operation(summary = "Editar status de curso", description = "Essa função é responsável por editar o status de um curso")
    public ResponseEntity<Object> toggleActiveCourse(@PathVariable UUID id) {
        this.toggleCourseActiveStatusUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar curso", description = "Essa função é responsável por deletar um curso")
    public ResponseEntity<Object> deleteCourse(
        @PathVariable UUID id
    ) {
        this.deleteCourseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}