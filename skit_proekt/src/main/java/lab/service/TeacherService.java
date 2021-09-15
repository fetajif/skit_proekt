package lab.service;

import lab.model.Teacher;
import lab.model.TeacherInterface;

import java.util.List;

public interface TeacherService {

    List<TeacherInterface> findAll();

    Teacher findById(Long id);

    Teacher save(Long id, String name, String surname);

}
