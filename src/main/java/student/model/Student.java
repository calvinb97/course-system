package student.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")

@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(
                name = "Student_FindAll",
                query = "from Student"
        ),
        @org.hibernate.annotations.NamedQuery(
                name = "Student_FindById",
                query = "from Student where id = :id"
        )
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SELECT)
    private List<Enrolment> enrolments;

    public Student() {
    }

//    public Student(int id, String lastName, String firstName) {
//        this.id = id;
//        this.lastName = lastName;
//        this.firstName = firstName;
//    }

    public Student(int id, String lastName, String firstName, List<Enrolment> enrolments) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.enrolments = enrolments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }
}
