package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Statement;

import java.util.List;

public interface AccountService extends BaseService<Account>{

    void create(Long id);
    List<Account> findByUserId(Long id);
    String getAccountStatementFileForDownload(Statement statement);
}
