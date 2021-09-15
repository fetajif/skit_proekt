package lab.service;

import lab.model.Course;
import lab.model.Student;
import lab.model.Type;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Student> listStudentsByCourse(Long courseId);

    Course addStudentInCourse(String username, Long courseId);

    Optional<Course> findById(Long id);

    List<Course> listAll();

    Optional<Course> save(String name, String description, Long teacherId, Type type);

    void deleteById(Long id);
}
