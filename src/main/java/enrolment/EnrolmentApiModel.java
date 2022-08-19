package enrolment;

public class EnrolmentApiModel {

    private int studentId;
    private int courseId;
    private int grade;

    public EnrolmentApiModel() {
    }

    public EnrolmentApiModel(int studentId, int courseId, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
