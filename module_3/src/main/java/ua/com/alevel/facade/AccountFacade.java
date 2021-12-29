package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.request.AccountStatementRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;

import java.util.List;

public interface AccountFacade extends BaseFacade<AccountRequestDto, AccountResponseDto>{

    void create(Long id);
    List<AccountResponseDto> findByUserId(Long id);
    String getAccountStatementFileForDownload(AccountStatementRequestDto dto);
}