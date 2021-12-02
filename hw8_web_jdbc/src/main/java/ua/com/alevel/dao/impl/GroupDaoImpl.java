package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static ua.com.alevel.dao.query.JpaQueryUtil.CREATE_GROUP_QUERY;

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
    public List<Group> findAll() {
        return Collections.emptyList();
    }
}
