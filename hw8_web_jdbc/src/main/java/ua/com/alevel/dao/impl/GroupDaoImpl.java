package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;

import java.util.List;

@Service
public class GroupDaoImpl implements GroupDao {

//    private final JpaConfig jpaConfig;
//
//    public DepartmentDaoImpl(JpaConfig jpaConfig) {
//        this.jpaConfig = jpaConfig;
//    }

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
    public List<Group> findAll() {
        return null;
    }
}
