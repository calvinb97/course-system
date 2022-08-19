package enrolment;

public class EnrolmentResponseEntity {

    
    private String courseName;
    private String term;
    private int grade;

    public EnrolmentResponseEntity(String courseName, String term, int grade) {
        this.courseName = courseName;
        this.term = term;
        this.grade = grade;
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
