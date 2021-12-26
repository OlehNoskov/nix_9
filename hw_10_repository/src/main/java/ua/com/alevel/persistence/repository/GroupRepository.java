package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupRepository extends AbstractRepository<Group> {

    @Query(value = "select distinct(g) from Group as g join g.students as ge where ge.id in :ids")
    List<Student> findByStudents(@Param("ids") Long id);

//    @Query(value = "SELECT DISTINCT(s) FROM Student AS s JOIN course_student AS cs ON s.id = cs.student_id WHERE cs.course_id in :ids")
//    Set<Group> findStudentByGroupId(@Param("ids") Set<Long> students);

//    @Query(value = "SELECT COUNT(*) FROM mydb.course_student where course_id=",
//            nativeQuery = true)

    @Query(value = "select g.students.size from Group g where g.id = :id")
    Long findCount(@Param("id") Long id);
}