package lab.service;

import lab.model.Course;
import lab.model.Grade;
import lab.model.Student;

import java.util.List;

public interface GradeService {
    List<Grade> findAllByCourse(Course course);
    void save(Character grade, Student student, Course course);
}
