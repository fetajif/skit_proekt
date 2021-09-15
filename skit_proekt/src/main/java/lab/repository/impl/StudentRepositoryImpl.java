package lab.repository.impl;

import lab.bootstrap.DataHolder;
import lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl {
    /*public static List<Student> students=new ArrayList<Student>(5);


    public void init(){
        students.add(new Student("golubovaa", "andjelag", "Andjela", "Golubova"));
        students.add(new Student("golubovab", "blagicag", "Blagica", "Golubova"));
        students.add(new Student("fenevae", "elenaf", "Elena", "Feneva"));
        students.add(new Student("gerasimovak", "kristinag", "Kristina", "Gerasimova"));
        students.add(new Student("arsovae", "elenaa", "Elena", "Arsova"));
    }*/

    public List<Student> findAllStudents(){
        return DataHolder.students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return DataHolder.students.stream().filter(r->r.getName().contains(text) || r.getSurname().contains(text)).collect(Collectors.toList());
    }

    public Student save(Student s){
        if(s==null ||s.getUsername().isEmpty()){
            return null;
        }
        DataHolder.students.removeIf(r->r.getUsername().equals(s.getUsername()));
        DataHolder.students.add(s);
        return s;
    }
}
