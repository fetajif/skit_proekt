package lab.repository.jpa;

import lab.model.Teacher;
import lab.model.TeacherInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(nativeQuery = true, value = "select * from teacher")
    List<TeacherInterface> getTI();
}