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

    List<Group> findByNameStartingWithIgnoreCase(String name);
    Set<Group> findByVisibleTrue();

    @Query(value = "select distinct(g) from Group as g join g.students as ge where ge.id in :ids")
    List<Student> findByStudents(@Param("ids") Long id);

    @Query(value = "select g from Group as g join g.students as gs where gs.id in :ids")
    Set<Group> findByStudentIds(@Param("ids") Set<Long> students);
}
