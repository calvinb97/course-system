package course;

import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class CourseController {

    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseResponseEntity> getAllCourses(Request request, Response response) {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponseEntity> responseEntities = new ArrayList<>();
        for (Course course : courses) {
            responseEntities.add(new CourseResponseEntity(
                    course.getId(),
                    course.getCourseName(),
                    course.getTerm())
            );
        }
        return responseEntities;
    }

    public CourseResponseEntity getCourse(Request request, Response response, int id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id);
        CourseResponseEntity responseEntity = new CourseResponseEntity(
                course.getId(),
                course.getCourseName(),
                course.getTerm()
        );
        return responseEntity;
    }

}
