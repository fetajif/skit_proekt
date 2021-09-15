package lab.bootstrap;

import lombok.Getter;
import lab.model.Course;
import lab.model.Student;
import lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Course> courses;

    public static List<Student> students;

    public static List<Teacher> teachers;

    @PostConstruct
    public void init(){
        courses=new ArrayList<>();
        students=new ArrayList<>();
        teachers=new ArrayList<>();

        this.students.add(new Student("golubovaa", "andjelag", "Andjela", "Golubova"));
        this.students.add(new Student("golubovab", "blagicag", "Blagica", "Golubova"));
        this.students.add(new Student("fenevae", "elenaf", "Elena", "Feneva"));
        this.students.add(new Student("gerasimovak", "kristinag", "Kristina", "Gerasimova"));
        this.students.add(new Student("arsovae", "elenaa", "Elena", "Arsova"));


        this.courses.add(new Course("Veb Programiranje", "Kurs po Veb programiranje"));
        this.courses.add(new Course("Masinsko Ucenje", "Kurs po Masinsko ucenje"));
        this.courses.add(new Course("Vizuelizacija", "Kurs po Vizuelizacija"));
        this.courses.add(new Course("Implementacija na sistemi so otvoren kod", "Kurs po Implementacija na sistemi so otvoren kod"));
        this.courses.add(new Course("Multimediski sistemi", "Kurs po Multimediski sistemi"));

        //teachers.add(new Teacher("Riste", "Stojanov"));
        //teachers.add(new Teacher("Dimitar", "Trajanov"));
        //teachers.add(new Teacher("Kostadin", "Misev"));
        //teachers.add(new Teacher("Ana","Todorovska"));
        //teachers.add(new Teacher("Ivan", "Corbev"));

    }
}
