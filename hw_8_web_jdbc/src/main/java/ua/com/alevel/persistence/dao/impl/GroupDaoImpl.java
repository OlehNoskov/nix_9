package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public DataTableResponse findAll(DataTableRequest request) {

        List<Group> groups = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, namegroup count(*) as bookCount from group join student_group ab on student.id = ab.group_id group by group_id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                AuthorResultSet authorResultSet = convertResultSetToAuthor(resultSet);
                authors.add(authorResultSet.getAuthor());
                otherParamMap.put(authorResultSet.getAuthor().getId(), authorResultSet.getBookCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Author> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(authors);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from authors")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}