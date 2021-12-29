package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.TransactionRequestDto;
import ua.com.alevel.view.dto.response.TransactionResponseDto;

import java.util.List;

public interface TransactionFacade extends BaseFacade<TransactionRequestDto, TransactionResponseDto>{

    void create(TransactionRequestDto req, Long operationId);
    List<TransactionResponseDto> findTransactionsByUserId(Long userId);
    List<TransactionResponseDto> findTransactionsByAccountId(Long accountId);
}