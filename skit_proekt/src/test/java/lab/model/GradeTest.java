package lab.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    Teacher teacher = new Teacher(12345816L,"AA", "BB");
    Course course = new Course("Softversko Testiranje", "Skit", teacher, Type.MANDATORY);
    Student student = new Student("fjollaFetaji", "fjolla", "Fjolla", "Fetaji");
    Grade grade = new Grade('A', student, course);

    @Test
    void getGrade() {
        assertEquals(grade.getGrade(), 'A');
    }

    @Test
    void getStudent() {
        assertEquals(grade.getStudent(), student);
    }

    @Test
    void getCourse() {
        assertEquals(grade.getCourse(), course);
    }

    @Test
    void setStudent() {
        Student student1 = new Student("fjollaF", "fjolla", "Fjolla", "Fetaji");
        grade.setStudent(student1);
        assertEquals(grade.getStudent(), student1);
    }

    @Test
    void setCourse() {
        Course course1 = new Course("SKIT", "Skit", teacher, Type.SUMMER);
        grade.setCourse(course1);
        assertEquals(grade.getCourse(), course1);
    }

    @Test
    void testToString() {
        String str = "Grade(id=" + grade.getId() + ", grade=" + grade.getGrade() +
                ", student=" + grade.getStudent() + ", course=" + grade.getCourse() + ")";
        assertEquals(grade.toString(), str);
    }

    public static Collection<Object[]> dataSource() {
        Teacher teacher = new Teacher(12345816L,"AA", "BB");
        Course course = new Course("Softversko Testiranje", "Skit", teacher, Type.MANDATORY);
        Student student = new Student("fjollaFetaji", "fjolla", "Fjolla", "Fetaji");

        return Arrays.asList(new Object[][] {
                {new Grade('A', student, course), "Excellent -> 10/A"},
                {new Grade('B', student, course), "Very Good -> 9/B"},
                {new Grade('C', student, course), "Good -> 8/C"},
                {new Grade('D', student, course), "Okay -> 7/D"},
                {new Grade('E', student, course), "Passed -> 6/E"},
                {new Grade('F', student, course), "Failed -> 5/F"}
        });
    }

    @ParameterizedTest
    @MethodSource("dataSource")
    void testGetGradeExtended(Grade grade, String output) {
        assertEquals(output, grade.getGradeExtended());
    }
}