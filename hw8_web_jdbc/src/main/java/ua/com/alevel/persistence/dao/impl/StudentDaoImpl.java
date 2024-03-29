package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.*;

import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.*;

@Service
public class StudentDaoImpl implements StudentDao {

    private final JpaConfig jpaConfig;

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Student student) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_STUDENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(student.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(student.getUpdated().getTime()));
            preparedStatement.setBoolean(3, new Boolean(student.getVisible()));
            preparedStatement.setString(4, student.getFirstname());
            preparedStatement.setString(5, student.getLastname());
            preparedStatement.setInt(6, student.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem new: = " + e.getMessage());
        }
    }

    @Override
    public void update(Student student) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_STUDENT_BY_ID_QUERY + student.getId())) {
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
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_STUDENT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_STUDENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Student findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_STUDENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initStudentByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        List<Student> students = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        Long groupId = null;
        if (request.getQueryMap().get("groupId") != null) {
            groupId = (Long) request.getQueryMap().get("groupId");
        }
        try (ResultSet resultSet = groupId == null ?
                jpaConfig.getStatement().executeQuery(FIND_ALL_STUDENTS_QUERY) :
                jpaConfig.getStatement().executeQuery(FIND_ALL_STUDENTS_BY_GROUP_ID_QUERY + groupId)) {
            while (resultSet.next()) {
                students.add(initStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }

        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setCurrentPage(request.getPageSize());
        dataTableResponse.setItems(students);
        dataTableResponse.setOtherParamMap(otherParamMap);

        return dataTableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from students")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Student initStudentByResultSet(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer age = resultSet.getInt("age");

        Student student = new Student();
        student.setId(id);
        student.setCreated(created);
        student.setUpdated(updated);
        student.setVisible(visible);
        student.setFirstname(firstName);
        student.setLastname(lastName);
        student.setAge(age);

        return student;
    }
}