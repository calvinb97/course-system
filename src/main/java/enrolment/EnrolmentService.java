package enrolment;

import course.Course;
import course.CourseNotFoundException;
import course.CourseRepository;
import student.Student;
import student.StudentNotFoundException;
import student.StudentRepository;

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
