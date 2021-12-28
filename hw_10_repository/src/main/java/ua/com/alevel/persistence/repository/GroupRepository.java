package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

@Repository
public interface GroupRepository extends AbstractRepository<Group> {

//    @Query(value = "SELECT * FROM students AS s JOIN course_student AS cs ON s.id = cs.student_id WHERE cs.course_id = ",
//            nativeQuery = true)
//    List<Student> findStudentsByGroupId(@Param("ids") Long id);
}