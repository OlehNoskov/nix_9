package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;
import ua.com.alevel.type.GroupType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ua.com.alevel.dao.query.JpaQueryUtil.*;

@Service
public class GroupDaoImpl implements GroupDao {

    private final JpaConfig jpaConfig;

    public GroupDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Group group) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_GROUP_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(group.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(group.getUpdated().getTime()));
            preparedStatement.setString(3, group.getGroupType().name());
            preparedStatement.setString(4, group.getNameGroup());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Group group) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_BY_ID_QUERY + group.getId())) {
            preparedStatement.setString(1, group.getNameGroup());
            preparedStatement.setString(2, group.getGroupType().name());
            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_GROUP_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_GROUP_BY_ID_QUERY + id)) {
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
    public Group findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_GROUP_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initGroupByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_GROUPS_QUERY)) {
            while (resultSet.next()) {
                groups.add(initGroupByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return groups;
    }

    private Group initGroupByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String type = resultSet.getString("group_type");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Group group = new Group();
        group.setId(id);
        group.setNameGroup(name);
        group.setGroupType(GroupType.valueOf(type));
        group.setCreated(new Date(created.getTime()));
        group.setUpdated(new Date(updated.getTime()));
        return group;
    }
}