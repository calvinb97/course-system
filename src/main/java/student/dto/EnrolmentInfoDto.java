package student.dto;

import student.model.Enrolment;

public class EnrolmentInfoDto {
    private int studentId;
    private String courseName;
    private String term;
    private int grade;

    public EnrolmentInfoDto() {
    }

    public EnrolmentInfoDto(Enrolment enrolment){
        this.studentId = enrolment.getStudent().getId();
        this.courseName = enrolment.getCourse().getCourseName();
        this.term = enrolment.getCourse().getTerm();
        this.grade = enrolment.getGrade();
    }

    public EnrolmentInfoDto(int studentId, String courseName, int grade) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
