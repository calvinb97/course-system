package course.api;

import course.dto.CourseBaseDto;
import course.model.Course;
import course.exception.CourseNotFoundException;
import course.dao.CourseRepository;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseBaseDto> getAllCourses(Request request, Response response) {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseBaseDto::new)
                .collect(Collectors.toList());
    }

    public CourseBaseDto getCourse(Request request, Response response, int id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id);
        return new CourseBaseDto(course);
    }

}
