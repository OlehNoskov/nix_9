package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;

import ua.com.alevel.persistence.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class StudentDaoImpl implements StudentDao {
    private final JpaConfig jpaConfig;

    public static final String CREATE_STUDENT_QUERY = "INSERT INTO studentsdb VALUES(default,?,?,?)";
    private static final String FIND_ALL_STUDENTS_QUERY = "select * from studentsdb";

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Student student) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_STUDENT_QUERY)) {
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setInt(3, student.getAge());
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }


    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse findAll(DataTableRequest request) {

        List<Student> students = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(CREATE_STUDENT_QUERY)) {
            while (resultSet.next()) {
                students.add(initStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(students);
        return dataTableResponse;
    }

    @Override
    public long count() {
        return 0;
    }

    private Student initStudentByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String studentName = resultSet.getString("firstname");
        String studentLastName = resultSet.getString("lastname");
        int age = resultSet.getInt("age");

        Student student = new Student();
        student.setId(id);
        student.setFirstname(studentName);
        student.setLastname(studentLastName);
        student.setAge(age);

        return student;
    }
}