package lab.model;

import lab.model.exception.TeacherNotFoundException;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course implements Comparable<Course> {
    @Id
    private Long courseId;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students=new ArrayList<Student>();

    }

    public Course(String name, String description, Teacher teacher) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students=new ArrayList<Student>();
        this.teacher = teacher;
    }

    public Course(String name, String description, Teacher teacher, Type type) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students=new ArrayList<Student>();
        this.teacher = teacher;
        this.type=type;
    }

    public Course() {

    }

    public Teacher throwingExceptions() {
        if (teacher == null)
            throw new TeacherNotFoundException(courseId);
        return teacher;
    }

    @Override
    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }
}
