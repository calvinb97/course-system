package student.dto;


import student.model.Student;

import java.util.List;

public class StudentWithCourseInfoDto extends StudentBaseDto {
    private List<EnrolmentInfoDto> enrolments;

    public StudentWithCourseInfoDto(Student student, List<EnrolmentInfoDto> enrolments){
        super(student);
        this.enrolments = enrolments;
    }

    public List<EnrolmentInfoDto> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<EnrolmentInfoDto> enrolments) {
        this.enrolments = enrolments;
    }
}
