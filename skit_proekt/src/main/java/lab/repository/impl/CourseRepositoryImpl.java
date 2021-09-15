package lab.repository.impl;

import lab.bootstrap.DataHolder;
import lab.model.Course;
import lab.model.Student;
import lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepositoryImpl {
    /*public static List<Course> courses=new ArrayList<Course>(5);

    public void init(){
        courses.add(new Course(1000,"Veb Programiranje", "Kurs po Veb programiranje",new ArrayList<Student>(Arrays.asList(new Student("golubovaa", "andjelag", "Andjela", "Golubova"), new Student("fenevae", "elenaf", "Elena", "Feneva")))));
        courses.add(new Course(2000,"Masinsko Ucenje", "Kurs po Masinsko ucenje",new ArrayList<Student>(Arrays.asList(new Student("golubovaa", "andjelag", "Andjela", "Golubova"), new Student("gerasimovak", "kristinag", "Kristina", "Gerasimova")))));
        courses.add(new Course(3000,"Vizuelizacija", "Kurs po Vizuelizacija",new ArrayList<Student>(Arrays.asList(new Student("gerasimovak", "kristinag", "Kristina", "Gerasimova"), new Student("fenevae", "elenaf", "Elena", "Feneva")))));
        courses.add(new Course(4000,"Implementacija na sistemi so otvoren kod", "Kurs po Implementacija na sistemi so otvoren kod",new ArrayList<Student>(Arrays.asList(new Student("gerasimovak", "kristinag", "Kristina", "Gerasimova"), new Student("golubovab", "blagicag", "Blagica", "Golubova")))));
        courses.add(new Course(5000,"Multimediski sistemi", "Kurs po Multimediski sistemi",new ArrayList<Student>(Arrays.asList(new Student("golubovab", "blagicag", "Blagica", "Golubova"), new Student("fenevae", "elenaf", "Elena", "Feneva")))));
    }*/

    public List<Course> findAllCourses(){
        return DataHolder.courses;
    }

 

    public Optional<Course> findById(Long courseId){
        return DataHolder.courses.stream().filter(r->r.getCourseId().equals(courseId)).findFirst();
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return DataHolder.courses.stream().filter(r->r.getCourseId().equals(courseId)).collect(Collectors.toList()).get(0).getStudents();
    }

    public Course addStudentToCourse(Student student, Course course){
        if(course==null || course.getName()==null || course.getName().isEmpty()){
            return null;
        }
        course.getStudents().add(student);
        return course;
    }

    public Optional<Course> save(String name, String description, Teacher teacher){
        DataHolder.courses.removeIf(r->r.getName().equals(name));
        Course course=new Course(name, description, teacher);
        DataHolder.courses.add(course);
        return Optional.of(course);

    }
    public void deleteById(Long id){
        DataHolder.courses.removeIf(r->r.getCourseId().equals(id));
    }


}
