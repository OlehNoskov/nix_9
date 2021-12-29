package ua.com.alevel.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.AccountStatement;
import ua.com.alevel.persistence.entity.AccountForFile;
import ua.com.alevel.service.AccountService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void create(Account entity) {
    }

    @Override
    public void create(Long id) {
        accountDao.create(id);
    }

    @Override
    public void update(Account entity) {
    }


    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() {
        List <Account> accountList = accountDao.findAll();
        return accountList;
    }

    @Override
    public List<Account> findByUserId(Long id) {
        return accountDao.findByUserId(id);
    }

    @Override
    public String getAccountStatementFileForDownload(AccountStatement accountStatement) {
        List <AccountForFile> accountForFileList = accountDao.getAccountStatementFileForDownload(accountStatement);

        String way = "src/main/resources";
        Path path = Paths.get(way);

        if (!Files.exists(path)) {
            way = "module_3/src/main/resources";
        }

        way += "/temp";
        path = Paths.get(way);

        if (!Files.exists(path)) {
            new File(way).mkdir();
        }

        way += "/account_" + accountStatement.getId() +"_statement.csv";

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(way));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Сумма", "Дата", "Доход/Расход")))
        {
            for (int i = 0; i < accountForFileList.size(); i++) {
                csvPrinter.printRecord(accountForFileList.get(i).getCategoryName(),
                        accountForFileList.get(i).getTransactionSum(),
                        accountForFileList.get(i).getOperationDate(),
                        accountForFileList.get(i).getIncomeExpense());
                csvPrinter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать файл-распечатку счета! Подробней в журнале.");
        }
        return way;
    }
}