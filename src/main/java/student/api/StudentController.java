package student.api;

import course.exception.CourseNotFoundException;
import student.dto.EnrolmentAddDto;
import student.dto.EnrolmentInfoDto;
import student.dto.StudentBaseDto;
import student.model.Enrolment;
import student.service.EnrolmentService;
import spark.Request;
import spark.Response;
import student.model.Student;
import student.exception.StudentNotFoundException;
import student.dao.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentController {

    private final StudentRepository studentRepository;
    private final EnrolmentService enrolmentService;



    public StudentController(StudentRepository studentRepository, EnrolmentService enrolmentService) {
        this.studentRepository = studentRepository;
        this.enrolmentService = enrolmentService;
    }

    public List<StudentBaseDto> getAllStudents(Request request, Response response) {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentBaseDto::new)
                .collect(Collectors.toList());
    }

    public StudentBaseDto getStudent(Request request, Response response, int id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id);
        return new StudentBaseDto(student);
    }

    public List<EnrolmentInfoDto> getEnrolments(Request req, Response res, int id) throws StudentNotFoundException {
        List<Enrolment> enrolments = enrolmentService.getEnrolmentsForStudentWithCourseInformation(id);
        return enrolments.stream()
                .map(EnrolmentInfoDto::new)
                .collect(Collectors.toList());
    }

    public EnrolmentInfoDto addEnrolment(
            Request req, Response res, int id, EnrolmentAddDto enrolmentRequest
    ) throws StudentNotFoundException, CourseNotFoundException {
        Enrolment result = enrolmentService.addCourseToStudent(id, enrolmentRequest.getCourseId());
        return new EnrolmentInfoDto(result);
    }
}
