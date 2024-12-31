package br.com.pedroveras.courses_api.exceptions;

public class CourseFoundException extends RuntimeException {
    public CourseFoundException() {
        super("Curso já existe");
      }
}
