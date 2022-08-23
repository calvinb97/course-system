package student.service;

import course.model.Course;
import course.exception.CourseNotFoundException;
import course.dao.CourseRepository;
import student.dao.EnrolmentRepository;
import student.dao.StudentRepository;
import student.model.*;
import student.exception.StudentNotFoundException;

import java.util.List;

public class EnrolmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrolmentRepository enrolmentRepository;

    public EnrolmentService(StudentRepository studentRepository,
                            CourseRepository courseRepository,
                            EnrolmentRepository enrolmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrolmentRepository = enrolmentRepository;
    }

    public List<Enrolment> getEnrolmentsForStudentWithCourseInformation(int id) throws StudentNotFoundException {
        Student student = studentRepository.findByIdWithCourseInformation(id);
        return student.getEnrolments();
    }

    public Enrolment addCourseToStudent(int studentId, int courseId)
            throws StudentNotFoundException, CourseNotFoundException {
        Student student = studentRepository.findById(studentId);
        Course course = courseRepository.findById(courseId);
        Enrolment enrolment = new Enrolment();
        enrolment.setStudent(student);
        enrolment.setCourse(course);
        enrolment.setId(new EnrolmentKey(student.getId(), course.getId()));
        return enrolmentRepository.save(enrolment);

    }
}
