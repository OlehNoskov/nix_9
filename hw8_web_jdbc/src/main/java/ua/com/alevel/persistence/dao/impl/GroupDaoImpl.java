package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.dao.query.JpaQueryUtil;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.*;

@Service
public class GroupDaoImpl implements GroupDao {

    private final JpaConfig jpaConfig;

    public GroupDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Group group) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_GROUP_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(group.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(group.getUpdated().getTime()));
            preparedStatement.setBoolean(3, group.getVisible());
            preparedStatement.setString(4, group.getNameGroup());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Group group) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_BY_ID_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(group.getUpdated().getTime()));
            preparedStatement.setString(2, group.getNameGroup());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_GROUP_BY_ID_QUERY + id);
            PreparedStatement preparedStatement2 = jpaConfig.getConnection().prepareStatement(FIND_ALL_STUDENTS_BY_GROUP_ID_QUERY + id);
            preparedStatement2.executeUpdate();
            preparedStatement.executeUpdate();

            ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PERSON_WITH_COUNTRY_COUNT_QUERY);
            while (resultSet.next()) {
                int count = resultSet.getInt("countryCount");
                if (count == 0) {
                    long personId = resultSet.getLong("id");
                    PreparedStatement preparedStatement3 = jpaConfig.getConnection().prepareStatement(DO_PERSON_NOT_VISIBLE_QUERY + personId);
                    preparedStatement3.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Group findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_GROUP_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initGroupByResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Group();
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        List<Group> companies = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.getQueryCompanyFindAll(request))) {
            while (resultSet.next()) {
                Company company = initCompanyByResultSet(resultSet);
                companies.add(company);
                otherParamMap.put(company.getId(), resultSet.getLong("counterparties"));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        ResponseDataTable<Company> responseDataTable = new ResponseDataTable<>();
        responseDataTable.setItems(companies);
        responseDataTable.setOtherParamMap(otherParamMap);
        return responseDataTable;
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

    private Group initGroupByResultSet(ResultSet resultSet) throws SQLException {
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

        return group;
    }

//    private record GroupResultSet(Group group, int studentCount) {
//
//        public Group getGroup() {
//            return group;
//        }
//
//        public int getStudentCount() {
//            return studentCount;
//        }
//    }
}