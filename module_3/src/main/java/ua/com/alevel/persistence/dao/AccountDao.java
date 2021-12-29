package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.AccountStatement;
import ua.com.alevel.persistence.entity.AccountForFile;

import java.util.List;

public interface AccountDao extends BaseDao <Account>{
    void create(Long id);
    List<Account> findByUserId(Long id);
    List <AccountForFile> getAccountStatementFileForDownload(AccountStatement accountStatement);
}