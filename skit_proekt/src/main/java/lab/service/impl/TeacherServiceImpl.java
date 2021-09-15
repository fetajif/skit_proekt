package lab.service.impl;

import lab.model.Teacher;
import lab.model.TeacherInterface;
import lab.repository.jpa.TeacherRepository;
import lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TeacherInterface> findAll() {
        /*Optional<Teacher> t=teacherRepository.findById(1L);
        if(t.isPresent()){
            Teacher teacher=t.get();
            List<Teacher> teachers=new ArrayList<>();
            teachers.add(teacher);
            return teachers;
        }*/
        return this.teacherRepository.getTI();
    }

    @Override
    public Teacher findById(Long id) {
        if(teacherRepository.findById(id).isPresent())
        {return teacherRepository.findById(id).get();}
        return null;
    }

    @Override
    public Teacher save(Long id, String name, String surname) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Teacher teacher= new Teacher(id, name, surname);
        teacherRepository.save(teacher);
        return  teacher;
    }

}