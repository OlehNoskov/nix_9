package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
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
    public void create(Group entity) {

    }

    @Override
    public void update(Group entity) {

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

        String sql = "select id, created, updated, visible, name, count(*) as studentCount from course join course_student ab on course.id = ab.student_id group by course_id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
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
        return 0;
    }

    private GroupResultSet convertResultSetToGroup(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String name = resultSet.getString("name");
        int studentCount = resultSet.getInt("studentCount");

        Group group = new Group();
        group.setId(id);
        group.setCreated(created);
        group.setUpdated(updated);
        group.setVisible(visible);
        group.setNameGroup(name);

        return new GroupResultSet(group, studentCount);
    }

    private static class GroupResultSet {

        private final Group group;
        private final int studentCount;


        private GroupResultSet(Group group, int studentCount) {
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

//
//    @Override
//    public void create(Group group) {
//        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_GROUP_QUERY)) {
//            preparedStatement.setTimestamp(1, new Timestamp(group.getCreated().getTime()));
//            preparedStatement.setTimestamp(2, new Timestamp(group.getUpdated().getTime()));
//            preparedStatement.setString(3, group.getNameGroup());
//            preparedStatement.setString(4, group.getGroupType().name());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void update(Group group) {
//        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_BY_ID_QUERY + group.getId())) {
//            preparedStatement.setString(1, group.getNameGroup());
//            preparedStatement.setString(2, group.getGroupType().name());
//            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_GROUP_BY_ID_QUERY + id)) {
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//    }
//
//    @Override
//    public boolean existById(Long id) {
//        long count = 0;
//        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_GROUP_BY_ID_QUERY + id)) {
//            while (resultSet.next()) {
//                count = resultSet.getLong("COUNT(*)");
//                System.out.println("count = " + count);
//            }
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//        return count == 1;
//    }
//
//    @Override
//    public Group findById(Long id) {
//        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_GROUP_BY_ID_QUERY + id)) {
//            while (resultSet.next()) {
//                return initGroupByResultSet(resultSet);
//            }
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//        return null;
//    }
//
//    @Override
//    public List<Group> findAll() {
//        List<Group> groups = new ArrayList<>();
//        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_GROUPS_QUERY)) {
//            while (resultSet.next()) {
//                groups.add(initGroupByResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//        return groups;
//    }
//
//    private Group initGroupByResultSet(ResultSet resultSet) throws SQLException {
//        long id = resultSet.getLong("id");
//        String name = resultSet.getString("name");
//        String type = resultSet.getString("group_type");
//        Timestamp created = resultSet.getTimestamp("created");
//        Timestamp updated = resultSet.getTimestamp("updated");
//        Group group = new Group();
//        group.setId(id);
//        group.setNameGroup(name);
//        group.setGroupType(GroupType.valueOf(type));
//        group.setCreated(new Date(created.getTime()));
//        group.setUpdated(new Date(updated.getTime()));
//        return group;
//    }
