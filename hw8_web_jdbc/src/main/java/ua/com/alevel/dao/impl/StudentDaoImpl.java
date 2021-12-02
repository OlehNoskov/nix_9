package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.type.GroupType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ua.com.alevel.dao.query.JpaQueryUtil.*;

@Service
public class StudentDaoImpl implements StudentDao {

    private final JpaConfig jpaConfig;

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Student student) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_STUDENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(student.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(student.getUpdated().getTime()));
            preparedStatement.setString(3, student.getFirstname());
            preparedStatement.setString(4, student.getLastname());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setLong(6, student.getGroup().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Student student) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_STUDENT_BY_ID_QUERY + student.getId())) {
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_STUDENT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_STUDENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
                System.out.println("count = " + count);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Student findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_STUDENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initStudentByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_STUDENTS_QUERY)) {
            while (resultSet.next()) {
                students.add(initStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return students;
    }

    @Override
    public void deleteAllByGroupId(Long groupId) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_STUDENTS_BY_GROUP_ID_QUERY + groupId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public List<Student> findAllByGroupId(Long groupId) {
        List<Student> students = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_STUDENTS_BY_GROUP_ID_QUERY + groupId)) {
            while (resultSet.next()) {
                students.add(initStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return students;
    }

    private Student initStudentByResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        Group group = new Group();
        long studentId = resultSet.getLong("stud.id");
        Timestamp studentCreated = resultSet.getTimestamp("stud.created");
        Timestamp studentUpdated = resultSet.getTimestamp("stud.updated");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        student.setId(studentId);;
        student.setFirstname(firstName);
        student.setLastname(lastName);
        student.setAge(age);
        student.setCreated(new Date(studentCreated.getTime()));
        student.setUpdated(new Date(studentUpdated.getTime()));

        long groupId = resultSet.getLong("group.id");
        Timestamp groupCreated = resultSet.getTimestamp("group.created");
        Timestamp groupUpdated = resultSet.getTimestamp("group.updated");
        String name = resultSet.getString("name");
        String groupType = resultSet.getString("group_type");

        group.setId(groupId);
        group.setNameGroup(name);
        group.setGroupType(GroupType.valueOf(groupType));
        group.setCreated(new Date(groupCreated.getTime()));
        group.setUpdated(new Date(groupUpdated.getTime()));

        student.setGroup(group);
        return student;
    }
}