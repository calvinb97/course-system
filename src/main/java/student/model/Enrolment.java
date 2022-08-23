package student.model;

import course.model.Course;

import javax.persistence.*;

@Entity
@Table(name = "enrolments")
public class Enrolment {

    @EmbeddedId
    private EnrolmentKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private int grade;

    @Enumerated(EnumType.STRING)
    private CourseRole role;

    public Enrolment() {
    }

    public Enrolment(EnrolmentKey id, Student student, Course course, int grade, CourseRole role) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.role = role;
    }

    public EnrolmentKey getId() {
        return id;
    }

    public void setId(EnrolmentKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public CourseRole getRole() {
        return role;
    }

    public void setRole(CourseRole role) {
        this.role = role;
    }
}
