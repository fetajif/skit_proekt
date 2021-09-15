package lab.repository.impl;

import lab.model.Teacher;
import lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepositoryImpl {

    public List<Teacher> findAll(){
        return DataHolder.teachers;
    }


    public Optional<Teacher> findById(Long id){
        return DataHolder.teachers.stream().filter(r->r.getId().equals(id)).findFirst();
    }
}
