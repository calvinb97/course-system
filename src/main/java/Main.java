import com.google.gson.Gson;
import course.api.CourseController;
import course.exception.CourseNotFoundException;
import course.dao.CourseRepository;
import spark.Filter;
import student.dao.EnrolmentRepository;
import student.dto.EnrolmentAddDto;
import student.service.EnrolmentService;
import org.hibernate.SessionFactory;
import student.model.Student;
import student.api.StudentController;
import student.exception.StudentNotFoundException;
import student.dao.StudentRepository;
import util.ContextProvider;
import util.JsonUtil;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = new ContextProvider().getSessionFactory();
        if (sessionFactory == null){
            System.out.println("Ohoh");
        }
        final StudentRepository studentRepository = new StudentRepository(sessionFactory);
        final CourseRepository courseRepository = new CourseRepository(sessionFactory);
        final EnrolmentRepository enrolmentRepository = new EnrolmentRepository(sessionFactory);

        final EnrolmentService enrolmentService = new EnrolmentService(
                studentRepository,
                courseRepository,
                enrolmentRepository
        );

        final StudentController studentController = new StudentController(studentRepository, enrolmentService);
        final CourseController courseController = new CourseController(courseRepository);

        // CORS HANDLING
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        // STUDENTS
        get("/students", studentController::getAllStudents, JsonUtil.json());
        get("/students/:id", (req, res) -> {
            try{
                int id = Integer.parseInt(req.params("id"));
                return studentController.getStudent(req, res, id);
            } catch (StudentNotFoundException e){
                return "Student nicht gefunden.";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, JsonUtil.json());
        get("students/:id/enrolments", (req, res) -> {
            try{
                int id = Integer.parseInt(req.params("id"));
                return studentController.getEnrolments(req, res, id);
            } catch (StudentNotFoundException e){
                return "Student nicht gefunden.";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, JsonUtil.json());
        post("students/:id/enrolments", (req, res) -> {
            try{
                int id = Integer.parseInt(req.params("id"));
                EnrolmentAddDto enrolmentRequest = new Gson().fromJson(req.body(), EnrolmentAddDto.class);
                return studentController.addEnrolment(req, res, id, enrolmentRequest);
            } catch (StudentNotFoundException e){
                return "Student nicht gefunden.";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, JsonUtil.json());


        post("/students", (req, res) -> {
            res.type("application/json");
            Student student = new Gson().fromJson(req.body(), Student.class);
            student = studentRepository.create(student);
            res.status(201);
            return student;
        }, JsonUtil.json());

        get("/test", (req, res) -> {
            res.status(201);
            return "Hello";
        }, JsonUtil.json());

        get("/courses", courseController::getAllCourses, JsonUtil.json());

        get("courses/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                return courseController.getCourse(req, res, id);
            } catch (CourseNotFoundException e) {
                return "Kurs nicht gefunden";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, JsonUtil.json());

        after((req, res) -> {
            res.type("application/json");
        });
    }
}