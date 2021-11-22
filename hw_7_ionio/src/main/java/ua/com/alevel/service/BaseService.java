package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;

public interface BaseService <ENTITY extends BaseEntity>{
    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findByID(String id);
    Collection<ENTITY> findByAll();
}