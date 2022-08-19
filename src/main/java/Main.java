import com.google.gson.Gson;
import course.Course;
import course.CourseController;
import course.CourseNotFoundException;
import course.CourseRepository;
import enrolment.Enrolment;
import enrolment.EnrolmentApiModel;
import enrolment.EnrolmentRepository;
import enrolment.EnrolmentService;
import org.hibernate.SessionFactory;
import student.Student;
import student.StudentController;
import student.StudentNotFoundException;
import student.StudentRepository;
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
                EnrolmentApiModel enrolmentRequest = new Gson().fromJson(req.body(), EnrolmentApiModel.class);
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