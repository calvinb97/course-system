package course.dto;

import course.model.Course;

public class CourseBaseDto {
    private int id;
    private String name;
    private String term;

    public CourseBaseDto() {
    }

    public CourseBaseDto(int id, String name, String term) {
        this.id = id;
        this.name = name;
        this.term = term;
    }

    public CourseBaseDto(Course course) {
        this.id = course.getId();
        this.name = course.getCourseName();
        this.term = course.getTerm();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
