package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupDaoImpl implements GroupDao {

    private final JpaConfig jpaConfig;

    public GroupDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Group group) {

    }

    @Override
    public void update(Group group) {

    }


    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Group findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {

        List<Group> groups = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, namegroup, count(*) as studentCount from group join student_group ab on student.id = ab.student_id group by student_id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                GroupResultSet groupResultSet = convertResultSetToGroup(resultSet);
                groups.add(groupResultSet.getGroup());
                otherParamMap.put(groupResultSet.getGroup().getId(), groupResultSet.getStudentCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Group> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(groups);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from group")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private GroupResultSet convertResultSetToGroup(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("namegroup");
//        String lastName = resultSet.getString("last_name");
        int studentCount = resultSet.getInt("studentCount");

        Group group = new Group();
        group.setId(group.getId());
        group.setNameGroup(group.getNameGroup());

        return new GroupResultSet(group, studentCount);
    }

    private static class GroupResultSet{
        private final Group group;
        private final int studentCount;

        public GroupResultSet(Group group, int studentCount) {
            this.group = group;
            this.studentCount = studentCount;
        }

        public Group getGroup() {
            return group;
        }

        public int getStudentCount() {
            return studentCount;
        }
    }
}