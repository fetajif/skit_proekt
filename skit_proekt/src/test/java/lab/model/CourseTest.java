package lab.model;

import lab.model.exception.TeacherNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Teacher teacher = new Teacher(12345816L,"AA", "BB");
    Course course = new Course("Softversko Testiranje", "Skit", teacher, Type.MANDATORY);

    @Test
    void compareTo() {
        assertTrue(course.compareTo(new Course("Softversko Testiranje", "Skitt", teacher, Type.SUMMER)) == 0);
    }

    @Test
    void getName() {
        assertEquals(course.getName(), "Softversko Testiranje");
    }

    @Test
    void getDescription() {
        assertEquals(course.getDescription(), "Skit");
    }

    @Test
    void getTeacher() {
        assertEquals(course.getTeacher(), teacher);
    }

    @Test
    void getType() {
        assertEquals(course.getType(), Type.MANDATORY);
    }

    @Test
    void setCourseId() {
        course.setCourseId(12345678L);
        assertEquals(course.getCourseId(),12345678L);
    }

    @Test
    void setName() {
        course.setName("SKIT");
        assertEquals(course.getName(), "SKIT");
    }

    @Test
    void setDescription() {
        course.setDescription("Softverski kvalitet i testiranje");
        assertEquals(course.getDescription(), "Softverski kvalitet i testiranje");
    }

    @Test
    void setTeacher() {
        Teacher teacher1 = new Teacher(123456L, "test1", "test2");
        course.setTeacher(teacher1);
        assertEquals(course.getTeacher(), teacher1);
    }

    @Test
    void setType() {
        course.setType(Type.ELECTIVE);
        assertEquals(course.getType(), Type.ELECTIVE);
    }

    @org.junit.Test(expected=TeacherNotFoundException.class)
    void testException() {
        course.setTeacher(null);
        course.throwingExceptions();
        //Assertions.assertThrows(TeacherNotFoundException.class, (Executable) course.throwingExceptions());
    }
}