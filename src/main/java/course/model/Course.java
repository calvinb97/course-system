package course.model;

import student.model.Enrolment;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(
                name = "Course_FindAll",
                query = "from Course"
        ),
        @org.hibernate.annotations.NamedQuery(
                name = "Course_FindById",
                query = "from Course where id = :id"
        )
})
@Entity
@Table (name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String courseName;
    @Column(name="term")
    private String term;

    @OneToMany(mappedBy = "course", cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SELECT)
    private List<Enrolment> enrolments;

    public Course() {
    }

//    public Course(int id, String courseName, String term) {
//        this.id = id;
//        this.courseName = courseName;
//        this.term = term;
//    }

    public Course(int id, String courseName, String term, List<Enrolment> enrolments) {
        this.id = id;
        this.courseName = courseName;
        this.term = term;
        this.enrolments = enrolments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }
}
