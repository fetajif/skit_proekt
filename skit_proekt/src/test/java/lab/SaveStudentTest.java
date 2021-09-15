package lab;

import lab.model.Student;
import lab.repository.jpa.CourseRepository;
import lab.repository.jpa.StudentRepository;
import lab.service.CourseService;
import lab.service.StudentService;
import lab.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SaveStudentTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Student student = new Student("fjolla","fjolla","Fjolla","Fetaji");
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        this.studentService = Mockito.spy(new StudentServiceImpl(this.studentRepository));
    }

    @Test
    public void testSuccessRegister() {

        Student student = this.studentService.save("fjolla","fjolla","Fjolla","Fetaji");
        Mockito.verify(this.studentService).save("fjolla","fjolla","Fjolla","Fetaji");


        Assert.assertNotNull("Manufacturer is null", student);
        Assert.assertEquals("name do not mach", "Fjolla", student.getName());
        Assert.assertEquals("surname do not match", "Fetaji", student.getSurname());
        Assert.assertEquals("username do not mach", "fjolla", student.getUsername());
        Assert.assertEquals("password do not mach", "fjolla", student.getPassword());

    }

    /*
    @Test
    public void testNullUsername() {
        Assertions.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save(null, "ag","AA","AA"));
        Mockito.verify(this.studentService).save(null, "ag","AA","AA");
    }
    */
}
