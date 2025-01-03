package br.com.pedroveras.courses_api.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Curso não existe");
      }
}
