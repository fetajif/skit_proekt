package lab.service.impl;

import lab.model.Course;
import lab.model.Grade;
import lab.model.Student;
import lab.repository.jpa.GradeRepository;
import lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl (GradeRepository gradeRepository){
        this.gradeRepository=gradeRepository;
    }

    @Override
    public List<Grade> findAllByCourse(Course course) {
        return gradeRepository.findAllByCourse(course);
    }

    @Override
    public void save(Character grade, Student student, Course course) {
        Grade g = new Grade(grade, student, course);
        gradeRepository.save(g);
    }
}
