package lab.service.impl;

import lab.model.Course;
import lab.model.Student;
import lab.model.Teacher;
import lab.model.Type;
import lab.repository.jpa.CourseRepository;
import lab.service.CourseService;
import lab.service.StudentService;
import lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiseImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;


    public CourseServiseImpl(CourseRepository courseRepository, StudentService studentService, TeacherService teacherService){
        this.courseRepository=courseRepository;
        this.studentService=studentService;
        this.teacherService = teacherService;

    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Optional<Course> c=this.courseRepository.findById(courseId);
        if(c.isPresent()){
            Course course=c.get();
            return course.getStudents();
        }
        return null;
    }

    public Optional<Course> findCourseById(Long Id) {
        if(Id == null) {
            return null;
        }
        return courseRepository.findById(Id);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student=studentService.findByUsername(username);
        Course course=findCourseById(courseId).get();
        List<Student> students= course.getStudents();
        students.add(student);
        course.setStudents(students);
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> save(String name, String description, Long teacherId, Type type) {
        if(this.courseRepository.findByName(name).isPresent()){
            Course c=this.courseRepository.findByName(name).get();
            Teacher teacher=this.teacherService.findById(teacherId);
            Course course=new Course(name, description, teacher, type);
            this.courseRepository.deleteById(c.getCourseId());
            return Optional.of(this.courseRepository.save(course));
        }
        else {
            Teacher teacher = this.teacherService.findById(teacherId);
            Course c = new Course(name, description, teacher, type);
            return Optional.of(this.courseRepository.save(c));
        }
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }
}
