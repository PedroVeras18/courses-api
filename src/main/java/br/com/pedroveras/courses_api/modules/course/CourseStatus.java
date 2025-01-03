package br.com.pedroveras.courses_api.modules.course;

public enum CourseStatus {
    ACTIVE, INACTIVE;

    public CourseStatus toggle() {
        return this == ACTIVE ? INACTIVE : ACTIVE;
    }
}