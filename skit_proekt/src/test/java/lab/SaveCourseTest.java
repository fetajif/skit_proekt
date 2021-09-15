package lab;

import lab.model.Course;
import lab.model.Student;
import lab.model.Teacher;
import lab.model.Type;
import lab.repository.jpa.CourseRepository;
import lab.repository.jpa.StudentRepository;
import lab.repository.jpa.TeacherRepository;
import lab.service.CourseService;
import lab.service.StudentService;
import lab.service.TeacherService;
import lab.service.impl.CourseServiseImpl;
import lab.service.impl.StudentServiceImpl;
import lab.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SaveCourseTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @Mock
    private CourseRepository courseRepository;
    private CourseService courseService;

    @Mock
    private TeacherRepository teacherRepository;
    private TeacherService teacherService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Student student = new Student("fjolla","fjolla","Fjolla","Fetaji");
        Teacher teacher = new Teacher(12345816L,"AA", "BB");
        Course course = new Course("Softversko Testiranje", "Skit", teacher, Type.MANDATORY);

        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        this.studentService = Mockito.spy(new StudentServiceImpl(this.studentRepository));

        Mockito.when(this.teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);
        this.teacherService = Mockito.spy(new TeacherServiceImpl(this.teacherRepository));

        Mockito.when(this.courseRepository.save(Mockito.any(Course.class))).thenReturn(course);
        this.courseService = Mockito.spy(new CourseServiseImpl(this.courseRepository, this.studentService, this.teacherService));

    }

    @Test
    public void testSaveTeacher() {

        Teacher teacher = this.teacherService.save(12345816L,"AA", "BB");
        Mockito.verify(this.teacherService).save(12345816L,"AA", "BB");

        Assert.assertNotNull("Manufacturer is null", teacher);
        Assert.assertEquals("name does not mach", "AA", teacher.getName());
        Assert.assertEquals("surname does not match", "BB", teacher.getSurname());
        Assert.assertEquals("id does not mach", java.util.Optional.of(12345816L).get(), teacher.getId());

    }

    @Test
    public void testSaveCourse() {

        Teacher teacher = this.teacherService.save(12345816L,"AA", "BB");
        Optional<Course> course = this.courseService.save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);
        Mockito.verify(this.courseService).save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);
        Course course1 = new Course("Softversko Testiranje", "Skit", teacher, Type.MANDATORY);

        Assert.assertNotNull("Manufacturer is null", course);
        Assert.assertEquals("name does not mach", "Softversko Testiranje", course.get().getName());
        Assert.assertEquals("description does not match", "Skit", course.get().getDescription());
        Assert.assertEquals("teacher id does not mach", Optional.of(12345816L).get(), teacher.getId());
        Assert.assertEquals("type does not match", Type.MANDATORY, course.get().getType());

    }

    // ------------------------------------------------------------
    // Testing Methods From CourseServiseImpl

    // Interface-Based Approach
    // Characteristic - C1: courseId is not null --> values: T or F
    // Test 1 of testListStudentsByCourse: C1-T
    @Test
    public void testListStudentsByCourseC1_T() {
        Teacher teacher = this.teacherService.save(12345816L,"AA", "BB");
        Optional<Course> course = this.courseService.save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);
        Mockito.verify(this.courseService).save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);

        Student student = this.studentService.save("fjolla","fjolla","Fjolla","Fetaji");
        Mockito.verify(this.studentService).save("fjolla","fjolla","Fjolla","Fetaji");

//        this.courseService.addStudentInCourse(student.getUsername(), course.get().getCourseId());
        List<Student> students = courseService.listStudentsByCourse(course.get().getCourseId());

        Assert.assertNotEquals(students.size(), 0);
    }

    // Test 2 of testListStudentsByCourse: C1-F
    @Test
    public void testListStudentsByCourseC1_F() {
        Teacher teacher = new Teacher(12345816L,"AA", "BB");
        Course course = new Course("Softversko Testiranje", "Skit", teacher, Type.MANDATORY);
        List<Student> students = courseService.listStudentsByCourse(course.getCourseId());
        Assert.assertNull(students);
    }

    // Characteristic - C1: courseId is not null --> values: T or F
    // Test 1 of testFindCourseByIdC1_T: C1-T
    @Test
    public void testFindCourseByIdC1_T() {
        Teacher teacher = this.teacherService.save(12345816L,"AA", "BB");
        Optional<Course> course = this.courseService.save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);
        Mockito.verify(this.courseService).save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);

        Assert.assertNotNull(courseService.findById(course.get().getCourseId()));
    }

    // Test 2 of testFindCourseByIdC1_T: C1-F
    @Test
    public void testFindCourseByIdC1_F() {
        Teacher teacher = this.teacherService.save(12345816L,"AA", "BB");
        Optional<Course> course = this.courseService.save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);
        Mockito.verify(this.courseService).save("Softversko Testiranje", "Skit", teacher.getId(), Type.MANDATORY);

        Assert.assertEquals(Optional.empty(),courseService.findById(course.get().getCourseId()));
    }

}
