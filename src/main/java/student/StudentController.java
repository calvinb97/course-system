package student;

import course.CourseNotFoundException;
import enrolment.Enrolment;
import enrolment.EnrolmentApiModel;
import enrolment.EnrolmentResponseEntity;
import enrolment.EnrolmentService;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private final StudentRepository studentRepository;
    private final EnrolmentService enrolmentService;



    public StudentController(StudentRepository studentRepository, EnrolmentService enrolmentService) {
        this.studentRepository = studentRepository;
        this.enrolmentService = enrolmentService;
    }

    public List<StudentResponseEntity> getAllStudents(Request request, Response response) {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseEntity> responseEntities = new ArrayList<>();
        for (Student student : students) {
            responseEntities.add(new StudentResponseEntity(
                    student.getId(),
                    student.getLastName(),
                    student.getFirstName()
            ));
        }
        return responseEntities;
    }

    public StudentResponseEntity getStudent(Request request, Response response, int id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id);
        return new StudentResponseEntity(
                student.getId(),
                student.getLastName(),
                student.getFirstName()
        );
    }


    public List<EnrolmentResponseEntity> getEnrolments(Request req, Response res, int id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id);
        List<Enrolment> enrolments = student.getEnrolments();
        List<EnrolmentResponseEntity> responseEntities = new ArrayList<>();
        for (Enrolment enrolment : enrolments) {
            responseEntities.add(new EnrolmentResponseEntity(
                    enrolment.getCourse().getCourseName(),
                    enrolment.getCourse().getTerm(),
                    enrolment.getGrade()
            ));
        }
        return responseEntities;
    }

    public EnrolmentResponseEntity addEnrolment(
            Request req, Response res, int id, EnrolmentApiModel enrolmentRequest
    ) throws StudentNotFoundException, CourseNotFoundException {
        Enrolment result = enrolmentService.addCourseToStudent(id, enrolmentRequest.getCourseId());
        return new EnrolmentResponseEntity("bla", "bli", 1);
    }
}
