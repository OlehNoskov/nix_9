package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.dao.OperationDao;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.OperationService;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao;

    public OperationServiceImpl(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    @Override
    public void create(Operation entity) {
        operationDao.create(entity);
    }

    @Override
    public void update(Operation entity) {

    }

    @Override
    public void delete(Long id) {
        operationDao.delete(id);
    }

    @Override
    public Operation findById(Long id) {
        return operationDao.findById(id);
    }

    @Override
    public List<Operation> findAll() {
        return operationDao.findAll();
    }
}