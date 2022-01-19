package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Statement;
import ua.com.alevel.persistence.entity.AccountsData;

import java.util.List;

public interface AccountDao extends BaseDao <Account>{

    void create(Long id);
    List<Account> findByUserId(Long id);
    List <AccountsData> getAccountStatementFileForDownload(Statement statement);
}