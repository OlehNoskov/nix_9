package ua.com.alevel.db;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.io.IOException;
import java.util.Collection;

public interface BaseDB<ENTITY extends BaseEntity> {

    void create(ENTITY entity) throws IOException;

    void update(ENTITY entity);

    void delete(String id);

     ENTITY  findByID(String id);

    Collection<ENTITY> findAll();
}