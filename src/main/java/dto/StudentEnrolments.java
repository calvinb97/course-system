package dto;


import student.Student;

import java.util.List;

public class StudentEnrolments extends StudentBase {
    private List<EnrolmentInformation> enrolments;

    public StudentEnrolments(Student student, List<EnrolmentInformation> enrolments){
        super(student);
        this.enrolments = enrolments;
    }

    public List<EnrolmentInformation> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<EnrolmentInformation> enrolments) {
        this.enrolments = enrolments;
    }
}
