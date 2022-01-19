package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.view.dto.request.OperationRequestDto;
import ua.com.alevel.view.dto.response.OperationResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationFacadeImpl implements OperationFacade {

    private final OperationService operationService;

    public OperationFacadeImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void create(OperationRequestDto operationRequestDto) {
        Operation operation = new Operation();
        try {
            operation.setCategoryName(operationRequestDto.getOperationName());
            operation.setCategoryIncomeExpense(operationRequestDto.isCategoryIncomeExpense());
        } catch (NullPointerException ex) {
            throw new RuntimeException("Please fill in all fields");
        }
        operationService.create(operation);
    }

    @Override
    public void update(OperationRequestDto operationRequestDto) {
    }

    @Override
    public void delete(Long id) {
        operationService.delete(id);
    }

    @Override
    public OperationResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<OperationResponseDto> findAll() {
        List <Operation> operationList = operationService.findAll();
        List <OperationResponseDto> allOperations = new ArrayList<>();
        for (int i = 0; i < operationList.size(); i++) {
            allOperations.add(new OperationResponseDto(operationList.get(i)));
        }
        return allOperations;
    }
}