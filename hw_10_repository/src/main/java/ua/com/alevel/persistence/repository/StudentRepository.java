package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Student;


@Repository
public interface StudentRepository extends AbstractRepository<Student>{


//    "SELECT * FROM students AS s JOIN course_student AS cs ON s.id = cs.student_id WHERE cs.course_id = ";
}