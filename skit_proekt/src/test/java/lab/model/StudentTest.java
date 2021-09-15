package lab.model;

import static org.testng.Assert.*;

public class StudentTest {
    Student student = new Student("fjollaFetaji", "fjolla", "Fjolla", "Fetaji");

    @org.testng.annotations.Test
    public void testGetUsername() {
        assertEquals(student.getUsername(), "fjollaFetaji");
    }

    @org.testng.annotations.Test
    public void testGetPassword() {
        assertEquals(student.getPassword(), "fjolla");
    }

    @org.testng.annotations.Test
    public void testGetName() {
        assertEquals(student.getName(), "Fjolla");
    }

    @org.testng.annotations.Test
    public void testGetSurname() {
        assertEquals(student.getSurname(), "Fetaji");
    }

    @org.testng.annotations.Test
    public void testSuggestNewUsername() {
        String suggested = "F30fjolla";
        assertEquals(student.suggestNewUsername(), suggested);
    }
}