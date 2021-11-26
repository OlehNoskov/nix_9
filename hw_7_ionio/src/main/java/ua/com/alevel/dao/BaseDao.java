package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.io.IOException;
import java.util.Collection;

public interface BaseDao <ENTITY extends BaseEntity>{
    void create(ENTITY entity) throws IOException;
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findByID(String id);
    Collection<ENTITY> findAll();
}