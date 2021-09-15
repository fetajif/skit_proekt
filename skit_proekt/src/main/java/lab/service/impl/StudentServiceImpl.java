package lab.service.impl;

import lab.model.Student;
import lab.repository.jpa.StudentRepository;
import lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if (text==null || text.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if (username==null || username.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Student student= new Student(username, password, name, surname);
        studentRepository.save(student);
        return  student;
    }

    @Override
    public Student findByUsername(String username) {
        return this.studentRepository.findByUsername(username);
    }
}
