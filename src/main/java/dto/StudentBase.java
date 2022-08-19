package dto;

import student.Student;

public class StudentBase {
    private int id;
    private String lastName;
    private String firstName;

    public StudentBase() {
    }

    public StudentBase(int id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public StudentBase(Student student){
        this.id = student.getId();
        this.lastName = student.getLastName();
        this.firstName = student.getFirstName();
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
}
